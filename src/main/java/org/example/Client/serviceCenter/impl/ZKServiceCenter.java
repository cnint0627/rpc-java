package org.example.Client.serviceCenter.impl;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.example.Client.serviceCenter.ServiceCenter;

import java.net.InetSocketAddress;
import java.util.List;

public class ZKServiceCenter implements ServiceCenter {
    private CuratorFramework client;
    private static final String ROOT_PATH = "RPC_ROOT";
    public ZKServiceCenter() {
        RetryPolicy policy = new ExponentialBackoffRetry(100, 3);
        this.client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(40000)
                .retryPolicy(policy)
                .namespace(ROOT_PATH)
                .build();
        this.client.start();
        System.out.println("Zookeeper 连接成功");
    }
    @Override
    public InetSocketAddress serviceDiscovery(String serviceName) {
        try {
            List<String> addresses = client.getChildren().forPath("/" + serviceName);
            return parseAddress(addresses.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private InetSocketAddress parseAddress(String address) {
        String[] result = address.split(":");
        return new InetSocketAddress(result[0], Integer.parseInt(result[1]));
    }
}
