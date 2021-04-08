package com.nilin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@ApiModel(description = "All details about the User. ")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated user ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    @ApiModelProperty(notes = "The first name")
    @Size(max = 120)
    private String firstName;

    @Column(name = "LAST_NAME")
    @ApiModelProperty(notes = "The last name")
    @Size(max = 120)
    private String lastName;

    @Column(unique = true)
    @ApiModelProperty(notes = "The username")
    @NotBlank
    @Size(max = 20)
    private String username;

    @Column(name = "PASSWORD")
    @ApiModelProperty(notes = "The password")
    @JsonIgnore
    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "EMAIL")
    @ApiModelProperty(notes = "The email")
    private String email;

    @Column(name = "BIRTH_DAY")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "The birthday")
    private LocalDate birthday;

    @Column(name = "MOBILE")
    @ApiModelProperty(notes = "The mobile")
    private String mobile;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ApiModelProperty(notes = "The roles")
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /*public User(String firstName, String lastName, String username, String password,
                LocalDate birthday, String mobile, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.mobile = mobile;
    }*/
/*@OneToMany(cascade = CascadeType.ALL)
    private Set<Photo> photos;

    @OneToMany
    private Set<Album> albums;

    @OneToMany
    private Set<Profile> profiles;*/

    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public User(String name) {
        this.username = name;
    }
}
