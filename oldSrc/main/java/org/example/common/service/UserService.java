package org.example.common.service;

import org.example.common.annotation.Retryable;
import org.example.common.pojo.User;

public interface UserService {
    @Retryable
    User getUserByUid(String uid);
    void addUser(User u);
}
