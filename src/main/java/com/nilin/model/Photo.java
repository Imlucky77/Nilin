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

    private String name;

    private String description;

    @Lob
    //private MultipartFile[] data;
    private byte[] data;

    public Photo(String name) {
        this.name = name;
    }
}
