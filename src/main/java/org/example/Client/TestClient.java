package org.example.Client;

import org.example.Client.proxy.ClientProxy;
import org.example.common.pojo.User;
import org.example.common.service.UserService;

public class TestClient {
    public static void main(String[] args) {
        ClientProxy proxy = new ClientProxy("127.0.0.1", 1234);
        UserService userService = proxy.getProxy(UserService.class);
        for (int i = 0; i < 10; i++) {
            User user = userService.getUserByUid("test");
            System.out.println(user);
        }
    }
}
