package org.example.server.serviceregister.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.example.server.serviceregister.ServiceRegister;
import org.example.annotation.Retryable;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ZKServiceRegister implements ServiceRegister {
    private CuratorFramework client;
    private static final String ROOT = "RPC_ROOT";
    private static final String RETRY = "RPC_RETRY";
    public ZKServiceRegister() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(40000)
                .retryPolicy(policy)
                .namespace(ROOT)
                .build();
        this.client.start();
        log.info("Zookeeper 连接成功");
    }
    @Override
    public void register(Class<?> clazz, InetSocketAddress serviceAddress) {
        String serviceName = clazz.getName();
        try {
            String servicePath = "/" + serviceName;
            String serviceAddressPath = servicePath + "/" + getAddress(serviceAddress);
            if (client.checkExists().forPath(servicePath) == null) {
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath);
                log.info("服务节点 {} 创建成功", servicePath);
            }
            if (client.checkExists().forPath(serviceAddressPath) == null) {
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath(serviceAddressPath);
                log.info("服务地址 {} 注册成功", serviceAddressPath);
            } else {
                log.info("服务地址 {} 已经存在，跳过注册", serviceAddressPath);
            }
            for (String methodSignature: getRetryableMethods(clazz)) {
                // 注册到重试白名单
                CuratorFramework retryClient = client.usingNamespace(RETRY);
                if (retryClient.checkExists().forPath(servicePath + "/" + methodSignature) == null) {
                    retryClient.create()
                            .creatingParentsIfNeeded()
                            .withMode(CreateMode.EPHEMERAL)
                            .forPath(servicePath + "/" + methodSignature);
                    log.info("可重试方法 {} 注册成功", servicePath + "/" + methodSignature);
                } else {
                    log.info("可重试方法 {} 已经存在，跳过注册", servicePath + "/" + methodSignature);
                }
            }
        } catch (Exception e) {
            log.error("服务 {} 注册失败: {}", serviceName, e.getMessage());
        }
    }
    private String getAddress(InetSocketAddress serviceAddress) {
        return serviceAddress.getHostName() + ":" + serviceAddress.getPort();
    }
    private List<String> getRetryableMethods(Class<?> clazz) {
        List<String> retryableMethods = new ArrayList<>();
        for (Method method: clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Retryable.class)) {
                String methodSignature = getMethodSignature(method);
                retryableMethods.add(methodSignature);
            }
        }
        return retryableMethods;
    }
    private String getMethodSignature(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName()).append("(");
        Class<?>[] paramTypes = method.getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            sb.append(paramTypes[i].getName());
            if (i < paramTypes.length - 1) {
                sb.append(",");
            } else {
                sb.append(")");
            }
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        return "Zookeeper";
    }
}
