package com.nilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Photo> photos;

    public Users(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
