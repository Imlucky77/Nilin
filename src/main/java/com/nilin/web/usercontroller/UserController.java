package com.nilin.web.usercontroller;

import com.nilin.model.Users;
import com.nilin.services.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // -------------------Create a Users-------------------------------------------
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        if (userService.isUserExist(user)) {
            return new ResponseEntity<>("Unable to create. A Users with name " +
                    user.getName() + " already exist.", HttpStatus.CONFLICT);
        }
        userService.save(user);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    // -------------------Retrieve All Users---------------------------------------------
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<Users>> listAllUsers() {
        List<Users> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // -------------------Retrieve Single Users------------------------------------------

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("userId") long id) {
        Users user = userService.findAllById(id);
        if (user == null) {
            return new ResponseEntity<>("Users with id " + id
                    + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // ------------------- Delete All Users-----------------------------
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<Users> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete a Users-----------------------------------------
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("userId") long id) {

        Users user = userService.findAllById(id);
        if (user == null) {
            return new ResponseEntity<>("Unable to delete. Users with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Update a Users ------------------------------------------------
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("userId") long id, @RequestBody Users user) {

        Users currentUser = userService.findAllById(id);

        if (currentUser == null) {
            return new ResponseEntity<>("Unable to update. Users with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());

        userService.updateUser(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
