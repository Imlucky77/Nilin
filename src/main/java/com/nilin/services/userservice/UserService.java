package com.nilin.services.userservice;

import com.nilin.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User username);


    void updateUser(User username);

    User findAllById(Long id);

    Optional<User> findByUsername(String username);

    void deleteUserById(Long id);

    void deleteAllUsers();

    boolean isUserExist(User username);

    //
    User createUser(User user);

    List<User> findAllUsers();

}
