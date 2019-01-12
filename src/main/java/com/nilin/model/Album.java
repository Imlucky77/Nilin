package com.nilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ALBUM")
public class Album {

    @Column(name = "Title")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATED")
    private LocalDate created;

   /* @ManyToOne
    @Column(name = "USER")
    private User user;*/

   /* @OneToMany
    @Column(name = "PHOTOS")
    private List<Photo> photos;*/

}
