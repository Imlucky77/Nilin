package com.nilin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "album")
@ApiModel(description = "All details about the Album. ")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated album ID")
    private Long id;

    @Column(name = "NAME")
    @ApiModelProperty(notes = "The album name")
    private String name;
    @Column(name = "Title")
    @ApiModelProperty(notes = "The album title")
    private String title;
    @Column(name = "DESCRIPTION")
    @ApiModelProperty(notes = "The album description")
    private String description;
    @Column(name = "CREATED")
    @ApiModelProperty(notes = "The album created")
    private LocalDate created;

    /*@ManyToOne
    private User user;*/

}
