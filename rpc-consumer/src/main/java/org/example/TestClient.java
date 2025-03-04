package org.example;

import org.example.client.proxy.ClientProxy;
import org.example.pojo.User;
import org.example.service.UserService;

public class TestClient {
    public static void main(String[] args) {
        RpcApplication.initialize();
        ClientProxy proxy = new ClientProxy();
        UserService userService = proxy.getProxy(UserService.class);
        for (int i = 0; i < 3; i++) {
            User user = userService.getUserByUid("test");
            System.out.println(user);
        }
    }
}
