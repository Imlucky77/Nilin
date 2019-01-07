package com.nilin.web.profilecontroller;

import com.nilin.model.Profile;
import com.nilin.repositories.profilerepository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
public class UploadProfileController {

    @Autowired
    ProfileRepository repository;

    /*
     * MultipartFile Upload
     */
    @PostMapping("/api/file/uploads")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file) {
        try {
            // save file to PostgreSQL
            //Profile filemode = new Profile(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            LocalDate date = LocalDate.of(2019, 01, 07);
            Profile filemode = new Profile("profile", "imlucky", date, file.getContentType(), file.getBytes());
            repository.save(filemode);
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }
    }
}
