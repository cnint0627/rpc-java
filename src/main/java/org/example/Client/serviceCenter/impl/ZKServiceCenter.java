package org.example.Client.serviceCenter.impl;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.example.Client.cache.ServiceCache;
import org.example.Client.serviceCenter.ServiceCenter;
import org.example.Client.serviceCenter.ZKWatcher.ZKWatcher;
import org.example.Client.serviceCenter.balance.LoadBalance;
import org.example.Client.serviceCenter.balance.impl.ConsistencyHashLoadBalance;

import java.net.InetSocketAddress;
import java.util.List;

public class ZKServiceCenter implements ServiceCenter {
    private CuratorFramework client;
    private ServiceCache cache;
    private LoadBalance balance;
    private static final String ROOT_PATH = "RPC_ROOT";
    private static final String RETRY_PATH = "RPC_RETRY";
    public ZKServiceCenter() throws InterruptedException {
        // Zookeeper客户端
        RetryPolicy policy = new ExponentialBackoffRetry(100, 3);
        this.client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(40000)
                .retryPolicy(policy)
                .namespace(ROOT_PATH)
                .build();
        this.client.start();
        System.out.println("Zookeeper 连接成功");
        // 监听缓存变化
        this.cache = new ServiceCache();
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
                    System.out.println(serviceName + " 服务当前不可用");
                    return null;
                }
            }
            String address = balance.balance(addressList);
            return parseAddress(address);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean checkRetry(String serviceName) {
        try {
            // 先从本地缓存获取
            List<String> serviceList = cache.getServiceFromCache(RETRY_PATH);
            if (serviceList == null) {
                // 再从Zookeeper获取
                serviceList = client.getChildren().forPath("/" + RETRY_PATH);
            }
            return serviceList.contains(serviceName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private InetSocketAddress parseAddress(String address) {
        String[] result = address.split(":");
        return new InetSocketAddress(result[0], Integer.parseInt(result[1]));
    }
}
