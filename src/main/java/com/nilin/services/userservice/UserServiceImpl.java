package com.nilin.services.userservice;

import com.nilin.model.User;
import com.nilin.repositories.userrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User username) {
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
