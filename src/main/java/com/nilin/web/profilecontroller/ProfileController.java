package com.nilin.web.profilecontroller;

import com.nilin.model.Profile;
import com.nilin.repositories.profilerepository.ProfileRepository;
import com.nilin.services.profileservice.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@RestController
public class ProfileController {

    @Autowired
    ProfileService service;

    /*@PostMapping("/image")
    public void run() throws Exception {
        // image 1
        ClassPathResource backImgFile = new ClassPathResource("/image/java.jpeg");
        byte[] arrayPic = new byte[(int) backImgFile.contentLength()];
        backImgFile.getInputStream().read(arrayPic);

        LocalDate a = LocalDate.of(2014, 6, 30);
        Profile blackImage = new Profile(1L, "java", "picNilin", a, "jpeg", arrayPic);

        // store image to MySQL via SpringJPA
        profileRepository.save(blackImage);
        //profileRepository.save(blueImage);

        // retrieve image from MySQL via SpringJPA
        for (Profile imageModel : profileRepository.findAll()) {
            Files.write(Paths.get("/home/imlucky/Downloads/Nilin/src/main/resources/retrieve-dir/" + imageModel.getFirstName() + "." + imageModel.getType()), imageModel.getPic());
        }
    }*/
}
