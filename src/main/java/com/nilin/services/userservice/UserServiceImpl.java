package com.nilin.services.userservice;

import com.nilin.model.Users;
import com.nilin.repositories.userrepository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void save(Users username) {
        userRepository.save(username);
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users findAllById(Long id) {
        return userRepository.findAllById(id);
    }

    @Override
    public Users findByName(String username) {
        return userRepository.findByName(username);
    }

    public void updateUser(Users username) {
        save(username);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public boolean isUserExist(Users user) {
        return findByName(user.getName()) != null;
    }
}
