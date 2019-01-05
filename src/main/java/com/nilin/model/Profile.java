package com.nilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDate birthday;

    @Column(name = "TYPE")
    private String type;

    @Lob
    @Column(name = "PICTURE")
    private byte[] pic;

    public Profile(Long id, String firstName, String lastName, LocalDate birthday, String type, byte[] pic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.type = type;
        this.pic = pic;
    }
}
