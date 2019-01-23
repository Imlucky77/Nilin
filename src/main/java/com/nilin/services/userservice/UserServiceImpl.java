package com.nilin.services.userservice;

import com.nilin.model.User;
import com.nilin.repositories.userrepository.UserRepository;
import com.nilin.util.BusinessException;
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

    public void save(User username) {
        userRepository.save(username);
    }

    public User createUser(User user) {
        if (isUserExist(user)) {
            throw new BusinessException(400, "Unable to create. A User with name " +
                    user.getUsername() + " already exist.");
        }
        return userRepository.save(user);
    }


    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            // I'm back 206 so I can print a message
            throw new BusinessException(404, "There is nothing to return");
        }
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
