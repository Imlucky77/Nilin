package com.nilin.services.userservice;

import com.nilin.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    void save(User username);

    List<User> findAllUsers();

    void updateUser(User username);

    User findAllById(Long id);

    User findByUsername(String username);

    void deleteUserById(Long id);

    void deleteAllUsers();

    boolean isUserExist(User username);

    //
    User createUser(User user);

}
