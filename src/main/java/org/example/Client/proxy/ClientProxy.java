package org.example.Client.proxy;

import org.example.Client.circuitBreaker.CircuitBreaker;
import org.example.Client.circuitBreaker.CircuitBreakerProvider;
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
    private CircuitBreakerProvider circuitBreakerProvider;
    public ClientProxy() throws InterruptedException {
        this.serviceCenter = new ZKServiceCenter();
        this.rpcClient = new GuavaRetryRpcClient(new NettyRpcClient(serviceCenter));
        this.circuitBreakerProvider = new CircuitBreakerProvider();
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
        String serviceName = request.getInterfaceName();
        CircuitBreaker circuitBreaker = circuitBreakerProvider.getCircuitBreaker(serviceName);
        if (circuitBreaker.allowRequest()) {
            // 是否触发服务熔断
            if (serviceCenter.checkRetry(serviceName)) {
                // 是否可以超时重试
                response = rpcClient.sendRequestWithRetry(request);
            } else {
                response = rpcClient.sendRequest(request);
            }
        } else {
            response = RpcResponse.fail("服务熔断");
            System.out.println(response);
            return null;
        }
        System.out.println(response);
        // 将请求状态上报给熔断器
        if (response.getCode() == 200) {
            circuitBreaker.recordSuccess();
        } else {
            circuitBreaker.recordFailure();
        }
        return response.getData();
    }
    public <T>T getProxy(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}
