package org.example.Client.proxy;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class ClientProxy implements InvocationHandler {
    private RetryRpcClient rpcClient;
    private ServiceCenter serviceCenter;
    private CircuitBreakerProvider circuitBreakerProvider;
    public ClientProxy() {
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
            if (serviceCenter.checkRetry(serviceName, getMethodSignature(method))) {
                // 是否可以超时重试
                response = rpcClient.sendRequestWithRetry(request);
            } else {
                response = rpcClient.sendRequest(request);
            }
        } else {
            log.error("服务 {} 熔断", serviceName);
            response = RpcResponse.fail("服务熔断");
        }
        // 将请求状态上报给熔断器
        if (response.getCode() == 200) {
            circuitBreaker.recordSuccess();
        } else {
            circuitBreaker.recordFailure();
        }
        log.info("服务 {} 响应: {}", serviceName, response.getMessage());
        return response.getData();
    }
    public <T>T getProxy(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
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
}
