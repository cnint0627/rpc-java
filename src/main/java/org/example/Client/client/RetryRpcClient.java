package org.example.Client.client;

import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

public interface RetryRpcClient extends RpcClient{
    RpcResponse sendRequestWithRetry(RpcRequest request);
}
