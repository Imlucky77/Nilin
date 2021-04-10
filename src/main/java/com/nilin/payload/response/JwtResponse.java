package com.nilin.payload.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String mobile;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, LocalDate birthday, String email, String firstName, String lastName, String mobile, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.birthday = birthday;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.roles = roles;
    }
}
