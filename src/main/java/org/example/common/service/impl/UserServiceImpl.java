package org.example.common.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.common.annotation.Retryable;
import org.example.common.pojo.User;
import org.example.common.service.UserService;

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
