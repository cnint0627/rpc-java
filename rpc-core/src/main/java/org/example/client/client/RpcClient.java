package org.example.client.client;

import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

public interface RpcClient {
    RpcResponse sendRequest(RpcRequest request);
}
