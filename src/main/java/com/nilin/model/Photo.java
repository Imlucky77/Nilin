package com.nilin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "photo")
@ApiModel(description = "All details about the Photo. ")
public class Photo implements Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "The database generated photo ID")
    private Long id;

    @ApiModelProperty(notes = "The photo name")
    private String name;

    @ApiModelProperty(notes = "The type photo")
    private String type;

    @ApiModelProperty(notes = "The photo data")
    @Lob
    private byte[] pic;

    public Photo(String name, String type, byte[] pic) {
        this.name = name;
        this.type = type;
        this.pic = pic;
    }

    public Photo(String name, byte[] pic) {
        this.name = name;
        this.pic = pic;
    }
}
