package org.example.Client;

import org.example.Client.proxy.ClientProxy;
import org.example.common.pojo.User;
import org.example.common.service.UserService;

public class TestClient {
    public static void main(String[] args) {
        ClientProxy proxy = new ClientProxy();
        UserService userService = proxy.getProxy(UserService.class);
        for (int i = 0; i < 3; i++) {
            User user = userService.getUserByUid("test");
            System.out.println(user);
        }
    }
}
