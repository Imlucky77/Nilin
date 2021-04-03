package com.nilin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "profile")
@Data
@NoArgsConstructor
@ApiModel(description = "All details about the Profile. ")
public class Profile {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated profile ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    @ApiModelProperty(notes = "The profile first name")
    private String firstName;

    @Column(name = "LAST_NAME")
    @ApiModelProperty(notes = "The profile last name")
    private String lastName;

    @Column(name = "BIRTH_DAY")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "The profile birthday")
    private LocalDate birthday;

    @Column(name = "MOBILE")
    @ApiModelProperty(notes = "The profile mobile")
    private String mobile;

    @Column(name = "EMAIL_ADDRESS")
    @ApiModelProperty(notes = "The profile email")
    private String email;

    @Column(name = "PICTURE")
    @ApiModelProperty(notes = "The profile picture")
    private String pic;

    public Profile(String firstName, String lastName, LocalDate birthday, String mobile, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.mobile = mobile;
        this.email = email;
    }
}
