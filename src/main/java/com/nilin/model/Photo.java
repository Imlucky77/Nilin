package com.nilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PHOTO")
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "Title")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    private Album album;
}
