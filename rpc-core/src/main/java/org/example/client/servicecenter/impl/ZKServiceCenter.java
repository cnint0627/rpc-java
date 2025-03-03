package org.example.client.servicecenter.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.example.client.cache.ServiceCache;
import org.example.client.servicecenter.ServiceCenter;
import org.example.client.servicecenter.ZKWatcher.ZKWatcher;
import org.example.client.servicecenter.balance.LoadBalance;
import org.example.client.servicecenter.balance.impl.ConsistencyHashLoadBalance;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ZKServiceCenter implements ServiceCenter {
    private CuratorFramework client;
    private ServiceCache cache;
    private LoadBalance balance;
    private static final String ROOT = "RPC_ROOT";
    private static final String RETRY = "RPC_RETRY";
    public ZKServiceCenter() {
        // Zookeeper客户端
        RetryPolicy policy = new ExponentialBackoffRetry(100, 3);
        this.client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(40000)
                .retryPolicy(policy)
                .namespace(ROOT)
                .build();
        this.client.start();
        log.info("Zookeeper 连接成功");
        // 本地地址缓存
        this.cache = new ServiceCache();
        // 监听缓存变化
        ZKWatcher zkWatcher = new ZKWatcher(client, cache);
        zkWatcher.watchToUpdate();
        // 负载均衡
        this.balance = new ConsistencyHashLoadBalance();
    }
    @Override
    public InetSocketAddress serviceDiscovery(String serviceName) {
        try {
            // 先从本地缓存获取
            List<String> addressList = cache.getServiceFromCache(serviceName);
            if (addressList == null) {
                // 再从Zookeeper获取
                addressList = client.getChildren().forPath("/" + serviceName);
                if (addressList.isEmpty()) {
                    log.error("{} 服务当前不可用", serviceName);
                    return null;
                }
            }
            String address = balance.balance(addressList);
            log.info("负载均衡选择了 {} 服务节点", address);
            return parseAddress(address);
        } catch (Exception e) {
            log.error("服务发现失败: {}", e.getMessage());
            return null;
        }
    }
    // 简单缓存可重试方法，实际应也使用Zookeeper Watcher实现
    private Map<String, List<String>> retryCache = new ConcurrentHashMap<>();
    @Override
    public boolean checkRetry(String serviceName, String methodName) {
        if (!retryCache.containsKey(serviceName)) {
            CuratorFramework retryClient = client.usingNamespace(RETRY);
            try {
                List<String> retryableMethods = retryClient.getChildren().forPath("/" + serviceName);
                retryCache.put(serviceName, retryableMethods);
            } catch (Exception e) {
                log.error("可重试方法检查失败: {}", e.getMessage());
                return false;
            }
        }
        return retryCache.get(serviceName).contains(methodName);
    }
    private InetSocketAddress parseAddress(String address) {
        String[] result = address.split(":");
        return new InetSocketAddress(result[0], Integer.parseInt(result[1]));
    }
}
