package com.nilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PROFILE")
@Data
@NoArgsConstructor
public class Profile {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTH_DAY")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "PICTURE")
    private byte[] pic;

    public Profile(String firstName, String lastName, LocalDate birthday, String type, byte[] pic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.type = type;
        this.pic = pic;
    }
}
