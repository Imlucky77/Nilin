package com.nilin.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "ALBUM")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Title")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATED")
    private LocalDate created;

}
