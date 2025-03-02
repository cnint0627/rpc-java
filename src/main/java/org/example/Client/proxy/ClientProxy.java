package org.example.Client.proxy;

import org.example.Client.client.RetryRpcClient;
import org.example.Client.client.impl.GuavaRetryRpcClient;
import org.example.Client.client.impl.NettyRpcClient;
import org.example.Client.serviceCenter.ServiceCenter;
import org.example.Client.serviceCenter.impl.ZKServiceCenter;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxy implements InvocationHandler {
    private RetryRpcClient rpcClient;
    private ServiceCenter serviceCenter;
    public ClientProxy() throws InterruptedException {
        this.serviceCenter = new ZKServiceCenter();
        this.rpcClient = new GuavaRetryRpcClient(new NettyRpcClient(serviceCenter));
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsType(method.getParameterTypes())
                .build();
        RpcResponse response;
        if (serviceCenter.checkRetry(method.getDeclaringClass().getName())) {
            response = rpcClient.sendRequestWithRetry(request);
        } else {
            response = rpcClient.sendRequest(request);
        }
        return response.getData();
    }
    public <T>T getProxy(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}
