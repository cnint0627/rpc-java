package org.example.Client.client.impl;

import lombok.AllArgsConstructor;
import org.example.Client.client.RpcClient;
import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor
public class SimpleRpcClient implements RpcClient {
    private String host;
    private int port;

    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(request);
            out.flush();
            return (RpcResponse) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
