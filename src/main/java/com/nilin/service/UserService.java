package com.nilin.service;

import com.nilin.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
