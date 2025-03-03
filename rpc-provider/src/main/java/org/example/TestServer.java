package org.example;

import org.example.server.provider.ServiceProvider;
import org.example.server.server.RpcServer;
import org.example.server.server.impl.NettyRpcServer;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

public class TestServer {
    static String host;
    static int port;
    public static void main(String[] args) {
        RpcApplication.initialize();
        host = RpcApplication.getRpcConfig().getHost();
        port = RpcApplication.getRpcConfig().getPort();
        singleSystemTest();
    }

    static void singleSystemTest() {
        UserService userService = new UserServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider(host, port);
        serviceProvider.provideServiceInterface(userService);
        RpcServer rpcServer = new NettyRpcServer(serviceProvider);
//        new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//                rpcServer.stop();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
        rpcServer.start(port);
    }

    static void distributedSystemTest() {
        for (int i = 0; i < 10; i++) {
            int porti = port + i;
            new Thread(() -> {
                UserService userService = new UserServiceImpl();
                ServiceProvider serviceProvider = new ServiceProvider(host, porti);
                serviceProvider.provideServiceInterface(userService);
                RpcServer rpcServer = new NettyRpcServer(serviceProvider);
                rpcServer.start(porti);
            }).start();
        }
    }
}
