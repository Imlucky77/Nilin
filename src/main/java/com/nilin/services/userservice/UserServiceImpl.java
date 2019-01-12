package com.nilin.services.userservice;

import com.nilin.model.User;
import com.nilin.repositories.userrepository.UserRepository;
import com.nilin.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void save(User username) {

        if (userService.isUserExist(username)) {
            new CustomErrorType("Unable to create. A User with name " +
                    username.getUsername() + " already exist.");
        }
        userRepository.save(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findAllById(Long id) {
        return userRepository.findAllById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateUser(User username) {
        save(username);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public boolean isUserExist(User user) {
        return findByUsername(user.getUsername()) != null;
    }


}
