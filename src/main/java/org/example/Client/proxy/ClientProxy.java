package org.example.Client.proxy;

import lombok.AllArgsConstructor;
import org.example.Client.client.IOClient;
import org.example.Client.client.RpcClient;
import org.example.Client.client.impl.NettyRpcClient;
import org.example.Client.client.impl.SimpleSocketClient;
import org.example.common.message.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxy implements InvocationHandler {
    private RpcClient rpcClient;
    public ClientProxy(String host, int port) {
        this.rpcClient = new NettyRpcClient(host, port);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsType(method.getParameterTypes())
                .build();
        return rpcClient.sendRequest(request).getData();
    }
    public <T>T getProxy(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}
