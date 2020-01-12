package com.nilin.web.photoController;

import com.nilin.model.Photo;
import com.nilin.repositories.photorepository.PhotoRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@Api(value = "Photo Management System", description = "Operations pertaining to photo in Photo Management System")
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;

    @PostMapping("/upload")
    public Photo uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        Photo img = new Photo(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        final Photo savedImage = photoRepository.save(img);
        return savedImage;
    }

}
