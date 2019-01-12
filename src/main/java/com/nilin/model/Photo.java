package com.nilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Photo {

    private String title;
    private String description;

    @ManyToOne
    private Album album;
}
