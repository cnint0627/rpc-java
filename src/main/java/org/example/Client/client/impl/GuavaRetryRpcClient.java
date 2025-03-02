package org.example.Client.client.impl;

import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;
import org.example.Client.client.RetryRpcClient;
import org.example.Client.client.RpcClient;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
// 装饰者模式
public class GuavaRetryRpcClient implements RetryRpcClient {
    private RpcClient rpcClient;
    public GuavaRetryRpcClient(RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }
    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        return rpcClient.sendRequest(request);
    }
    @Override
    public RpcResponse sendRequestWithRetry(RpcRequest request) {
        String serviceName = request.getInterfaceName();
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                .retryIfException()
                .retryIfResult(response -> Objects.equals(response.getCode(), 500))
                .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("请求重试: 第 {} 次调用服务 {}", attempt.getAttemptNumber(), serviceName);
                    }
                })
                .build();
        try {
            return retryer.call(() -> rpcClient.sendRequest(request));
        } catch (Exception e) {
            log.error("服务 {} 请求超时", serviceName);
            return RpcResponse.fail("服务请求超时");
        }
    }
}
