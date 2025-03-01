package org.example.Server.server.impl;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.Server.server.work.WorkThread;
import org.example.Server.provider.ServiceProvider;
import org.example.Server.server.RpcServer;

import java.net.ServerSocket;
import java.net.Socket;

@RequiredArgsConstructor
public class SimpleRpcServer implements RpcServer {
    @NonNull
    private ServiceProvider serviceProvider;
    private boolean isStop;
    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务器已启动");
            while (!isStop) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void stop() {
        isStop = false;
    }
}
