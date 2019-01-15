package com.nilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PHOTO")
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Photo(String name) {
        this.name = name;
    }
}
