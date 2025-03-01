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
        UserService userService = new UserServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 1234);
        serviceProvider.provideServiceInterface(userService);
        RpcServer rpcServer = new NettyRpcServer(serviceProvider);
        new Thread(() -> {
            try {
                Thread.sleep(20000);
                rpcServer.stop();
            } catch (Exception e) {

            }
        }).start();
        rpcServer.start(1234);
    }
}
