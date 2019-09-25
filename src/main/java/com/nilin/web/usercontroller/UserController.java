package com.nilin.web.usercontroller;

import com.nilin.model.User;
import com.nilin.services.userservice.UserService;
import com.nilin.util.BusinessException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "User Management System", description = "Operations pertaining to user in User Management System")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Add a user")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(
            @ApiParam(value = "User object store in database table", required = true) @Valid
            @RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }

    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
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
    @ApiOperation(value = "Get a user by Id")
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(
            @ApiParam(value = "User id from which user object will retrieve", required = true)
            @PathVariable("userId") long id) {
        User user = userService.findAllById(id);
        if (user == null) {
            return new ResponseEntity<>("User with id " + id
                    + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // ------------------- Delete All User-----------------------------
    @ApiOperation(value = "Delete all users")
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete a User-----------------------------------------
    @ApiOperation(value = "Delete a user by Id")
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(
            @ApiParam(value = "User Id from which user object will delete from database table", required = true)
            @PathVariable("userId") long id
    ) {

        User user = userService.findAllById(id);
        if (user == null) {
            return new ResponseEntity<>("Unable to delete. User with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Update a User ------------------------------------------------
    @ApiOperation(value = "Update a user by Id")
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(
            @ApiParam(value = "User Id to update user object", required = true) @PathVariable("userId") long id,
            @ApiParam(value = "Update user object", required = true) @Valid @RequestBody User user) {

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
