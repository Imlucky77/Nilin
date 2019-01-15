package com.nilin.services.userservice;

import com.nilin.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(Users username);

    List<Users> findAllUsers();

    void updateUser(Users username);

    Users findAllById(Long id);

    Users findByName(String username);

    void deleteUserById(Long id);

    void deleteAllUsers();

    boolean isUserExist(Users username);

}
