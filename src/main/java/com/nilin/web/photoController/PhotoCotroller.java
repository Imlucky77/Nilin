package com.nilin.web.photoController;

import com.nilin.model.Photo;
import com.nilin.services.photoservice.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@Slf4j
public class PhotoCotroller {

    private PhotoService photoService;
    private Path rootLocation;

    public PhotoCotroller(PhotoService photoService, Path rootLocation) {
        this.photoService = photoService;
        this.rootLocation = rootLocation;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {

        Path file = this.rootLocation.resolve(filename);
        Resource resource = new UrlResource(file.toUri());

        return ResponseEntity
                .ok()
                .body(resource);
    }

    @PostMapping(value = "/photo")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, Photo photo) throws Exception {

        if (file.getSize() == 0) {
            return new ResponseEntity<>("File size is empty ", HttpStatus.NO_CONTENT);
        }

        String uuid = UUID.randomUUID().toString();

        String imagePath = this.rootLocation.resolve(uuid + ".jpg").toString();
        Files.copy(file.getInputStream(), this.rootLocation.resolve(imagePath));

        photoService.save(photo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*@PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
                                   Principal principal) throws Exception {

        if (file.getSize() == 0) {
            return "redirect:/";
        }

        String uuid = UUID.randomUUID().toString();

        String imagePath = this.rootLocation.resolve(uuid + ".jpg").toString();

        SiteUser user = userRepository.findByName(principal.getName()).orElseThrow(Exception::new);

        Set<Image> stringList = user.getImageList();
        stringList.add(new Image(imagePath));
        Files.copy(file.getInputStream(), this.rootLocation.resolve(imagePath));

        userRepository.save(user);

        return "redirect:/";
    }*/
}
