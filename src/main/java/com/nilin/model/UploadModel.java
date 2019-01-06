package com.nilin.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadModel {
    //private String extraField;

    private MultipartFile[] files;
}
