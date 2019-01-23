package com.nilin.web.usercontroller;

import com.nilin.model.User;
import com.nilin.services.userservice.UserService;
import com.nilin.util.BusinessException;
import com.nilin.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {
        try {
            List<User> allUsers = userService.findAllUsers();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }

    // -------------------Retrieve Single User------------------------------------------

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("userId") long id) {
        User user = userService.findAllById(id);
        if (user == null) {
            return new ResponseEntity<>("User with id " + id
                    + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // ------------------- Delete All User-----------------------------
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete a User-----------------------------------------
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("userId") long id) {

        User user = userService.findAllById(id);
        if (user == null) {
            return new ResponseEntity<>("Unable to delete. User with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Update a User ------------------------------------------------
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("userId") long id, @RequestBody User user) {

        User currentUser = userService.findAllById(id);

        if (currentUser == null) {
            return new ResponseEntity<>("Unable to update. User with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }

        currentUser.setUsername(user.getUsername());

        userService.updateUser(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
