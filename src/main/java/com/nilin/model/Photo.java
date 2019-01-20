package com.nilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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


    //private MultipartFile[] data;
    private String data;

    public Photo(String name) {
        this.name = name;
    }
}
