package com.nilin.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PROFILE")
@Data
public class Profile {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIRTH_DAY")
    private LocalDate birthday;
    @Column(name = "PICTURE")
    private byte[] pic;

    public Profile() {
    }
}
