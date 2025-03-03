package org.example.client.servicecenter;

import org.example.common.message.RpcRequest;

import java.net.InetSocketAddress;

public interface ServiceCenter {
    InetSocketAddress serviceDiscovery(String serviceName);
    boolean checkRetry(String serviceName, String methodName);
}
