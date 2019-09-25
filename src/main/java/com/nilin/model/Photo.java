package com.nilin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "All details about the Photo. ")
public class Photo {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "The database generated photo ID")
    private Long id;

    @ApiModelProperty(notes = "The photo name")
    private String name;

    @ApiModelProperty(notes = "The photo description")
    private String description;

    @ApiModelProperty(notes = "The photo data")
    private String data;

    public Photo(String name) {
        this.name = name;
    }
}
