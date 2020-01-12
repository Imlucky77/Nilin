package com.nilin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@ApiModel(description = "All details about the Client. ")
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated client ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    @ApiModelProperty(notes = "The client first name")
    private String firstName;

    @Column(name = "LAST_NAME")
    @ApiModelProperty(notes = "The client last name")
    private String lastName;

    @Column(name = "BIRTH_DAY")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "The client birthday")
    private LocalDate birthday;

    @Column(name = "MOBILE")
    @ApiModelProperty(notes = "The client mobile")
    private String mobile;

    @Column(name = "EMAIL_ADDRESS")
    @ApiModelProperty(notes = "The client email")
    private String email;

    @Column(name = "PICTURE")
    @ApiModelProperty(notes = "The client picture")
    private String pic;

    public Client(String firstName, String lastName, LocalDate birthday, String mobile, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.mobile = mobile;
        this.email = email;
    }
}
