package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.User;
import org.example.service.UserService;

import java.util.UUID;

@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUid(String uid) {
        log.info("查询uid为 {} 的用户", uid);
        return User.builder()
                .uid(UUID.randomUUID().toString())
                .name("Liu")
                .age(18)
                .build();
    }
    @Override
    public void addUser(User user) {
        log.info("插入用户 {}", user);
    }
}
