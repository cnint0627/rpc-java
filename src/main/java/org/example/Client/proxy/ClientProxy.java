package org.example.Client.proxy;

import lombok.AllArgsConstructor;
import org.example.Client.client.IOClient;
import org.example.Client.client.RpcClient;
import org.example.Client.client.impl.NettyRpcClient;
import org.example.Client.client.impl.SimpleSocketClient;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxy implements InvocationHandler {
    private RpcClient rpcClient;
    public ClientProxy() throws InterruptedException {
        this.rpcClient = new NettyRpcClient();
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsType(method.getParameterTypes())
                .build();
        RpcResponse response = rpcClient.sendRequest(request);
        if (response == null) {
            return null;
        }
        return response.getData();
    }
    public <T>T getProxy(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}
