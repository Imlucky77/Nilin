package com.nilin.web.usercontroller;

import com.nilin.exception.BusinessException;
import com.nilin.model.ERole;
import com.nilin.model.Role;
import com.nilin.model.User;
import com.nilin.payload.request.LoginRequest;
import com.nilin.payload.request.SignUpRequest;
import com.nilin.payload.response.JwtResponse;
import com.nilin.payload.response.MessageResponse;
import com.nilin.repositories.rolerepository.RoleRepository;
import com.nilin.repositories.userrepository.UserRepository;
import com.nilin.security.jwt.JwtUtils;
import com.nilin.security.services.UserDetailsImpl;
import com.nilin.services.userservice.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "User Management System", description = "Operations pertaining to user in User Management System")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @ApiOperation(value = "Add a user")
    @PostMapping(path = "/users/signup")
    public ResponseEntity<?> createUser(
            @ApiParam(value = "User object store in database table", required = true)
            @Valid @RequestBody SignUpRequest signUpRequest) {
        /*try {
            userService.createUser(user);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getStatus()));
        }*/

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        /*if (userRepository.existsByFirstName(signUpRequest.getFirstName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: FirstName is already in use!"));
        }


        if (userRepository.existsByLastName(signUpRequest.getLastName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: LastName is already in use!"));
        }


        if (userRepository.existsByMobile(signUpRequest.getMobile())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Mobile is already in use!"));
        }


        if (userRepository.existsByBirthday(signUpRequest.getBirthday())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: FirstName is already in use!"));
        }*/

        // Create new user's account
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/users/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                /*userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getBirthday(),
                userDetails.getMobile(),*/
                roles));
    }

    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> allUsers = userService.findAllUsers();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }

    // -------------------Retrieve Single User------------------------------------------
    @ApiOperation(value = "Get a user by Id")
    @GetMapping(value = "/users/{userId}")
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
    @DeleteMapping(value = "/users")
    public ResponseEntity<User> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete a User-----------------------------------------
    @ApiOperation(value = "Delete a user by Id")
    @DeleteMapping(value = "/users/{userId}")
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
    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<?> updateUser(
            @ApiParam(value = "User Id to update user object", required = true) @PathVariable("userId") long id,
            @ApiParam(value = "Update user object", required = true) @Valid @RequestBody User user) {

        User currentUser = userService.findAllById(id);

        if (currentUser == null) {
            return new ResponseEntity<>("Unable to update. User with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }

        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());

        userService.updateUser(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
