package org.example.service;

import org.example.annotation.Retryable;
import org.example.pojo.User;

public interface UserService {
    @Retryable
    User getUserByUid(String uid);
    void addUser(User u);
}
