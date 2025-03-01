package org.example.Server.server.impl;


import org.example.Server.server.work.WorkThread;
import org.example.Server.provider.ServiceProvider;
import org.example.Server.server.RpcServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRpcServer implements RpcServer {
    private final ThreadPoolExecutor threadPool;
    private ServiceProvider serviceProvider;
    private boolean isStop;
    public ThreadPoolRpcServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        this.threadPool = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                1000, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100)
        );
    }
    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务器已启动");
            while (!isStop) {
                Socket socket = serverSocket.accept();
                threadPool.execute(new WorkThread(socket, serviceProvider));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void stop() {
        isStop = true;
    }
}
