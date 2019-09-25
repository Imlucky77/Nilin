package com.nilin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@ApiModel(description = "All details about the User. ")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated user ID")
    private Long id;

    @Column(unique = true)
    @ApiModelProperty(notes = "The user username")
    private String username;

    @Column(name = "PASSWORD")
    @ApiModelProperty(notes = "The user password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Photo> photos;

    @OneToMany
    private Set<Album> albums;

    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public User(String s) {
        this.username = s;
    }
}
