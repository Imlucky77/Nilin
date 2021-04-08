package com.nilin.payload.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Size(max = 120)
    private String firstName;

    @Size(max = 120)
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;


    private String mobile;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public SignUpRequest(String username,String email,  String firstName, String lastName,
                         LocalDate birthday, String mobile, Set<String> role, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.mobile = mobile;
        this.role = role;
        this.password = password;
    }
}
