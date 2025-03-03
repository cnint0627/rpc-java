package org.example.server;

import org.example.server.provider.ServiceProvider;
import org.example.server.server.RpcServer;
import org.example.server.server.impl.NettyRpcServer;
import org.example.server.server.impl.SimpleRpcServer;
import org.example.server.server.impl.ThreadPoolRpcServer;
import org.example.common.service.UserService;
import org.example.common.service.impl.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        singleSystemTest();
    }

    static void singleSystemTest() {
        UserService userService = new UserServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 1234);
        serviceProvider.provideServiceInterface(userService);
        RpcServer rpcServer = new NettyRpcServer(serviceProvider);
        rpcServer.start(1234);
    }

    static void distributedSystemTest() {
        for (int i = 0; i < 10; i++) {
            int port = 1234 + i;
            new Thread(() -> {
                UserService userService = new UserServiceImpl();
                ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", port);
                serviceProvider.provideServiceInterface(userService);
                RpcServer rpcServer = new NettyRpcServer(serviceProvider);
                rpcServer.start(port);
            }).start();
        }
    }
}
