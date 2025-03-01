package org.example.common.service.impl;

import org.example.common.pojo.User;
import org.example.common.service.UserService;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUid(String uid) {
        System.out.println("查询uid为 " + uid + " 的用户");
        return User.builder()
                .uid(UUID.randomUUID().toString())
                .name("Liu")
                .age(18)
                .build();
    }
}
