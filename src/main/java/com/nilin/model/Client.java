package com.nilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Client")
@Data
@NoArgsConstructor
public class Client {


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

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "EMAIL_ADDRESS")
    private String email;

    @Column(name = "PICTURE")
    private byte[] pic;

    public Client(String firstName, String lastName, LocalDate birthday, byte[] pic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.pic = pic;
    }
}
