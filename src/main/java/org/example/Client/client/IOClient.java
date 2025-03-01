package org.example.Client.client;

import org.example.common.message.RpcRequest;
import org.example.common.message.RpcResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {
    public static RpcResponse sendRequest(String host, int port, RpcRequest request) {
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
