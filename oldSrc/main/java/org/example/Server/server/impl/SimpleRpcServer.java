package org.example.server.server.impl;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.server.server.work.WorkThread;
import org.example.server.provider.ServiceProvider;
import org.example.server.server.RpcServer;

import java.net.ServerSocket;
import java.net.Socket;

@RequiredArgsConstructor
public class SimpleRpcServer implements RpcServer {
    @NonNull
    private ServiceProvider serviceProvider;
    private ServerSocket serverSocket;
    @Override
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务端已启动");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void stop() {
        try {
            serverSocket.close();
            System.out.println("服务端已关闭");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
