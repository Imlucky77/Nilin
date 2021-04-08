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
    /*private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String mobile;*/
    private List<String> roles;

    /*public JwtResponse(String token, Long id, String username, String email, String firstName, String lastName, LocalDate birthday, String mobile, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.mobile = mobile;
        this.roles = roles;
    }*/

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
