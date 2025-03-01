package org.example.Server;

import org.example.Server.provider.ServiceProvider;
import org.example.Server.server.RpcServer;
import org.example.Server.server.impl.NettyRpcServer;
import org.example.Server.server.impl.SimpleRpcServer;
import org.example.Server.server.impl.ThreadPoolRpcServer;
import org.example.common.service.UserService;
import org.example.common.service.impl.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
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
