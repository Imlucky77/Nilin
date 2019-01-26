package com.nilin.web.photoController;

import com.nilin.model.Photo;
import com.nilin.services.photoservice.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public ResponseEntity<?> handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile[] fileUpload, Photo photo) {

        photo.setDescription(photo.getDescription());
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        File uploadRootDir = new File(uploadRootPath);

        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        List<File> uploadedFiles = new ArrayList<>();
        for (MultipartFile fileData : fileUpload) {

            // Client File Name
            String username = fileData.getOriginalFilename();
            photo.setName(username);

            if (username != null && username.length() > 0) {
                try {
                    // Create the file on server
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + username);


                    // Stream to write data to file in server.
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    //
                    uploadedFiles.add(serverFile);
                    photo.setData(serverFile.getAbsolutePath());
                } catch (Exception e) {
                    System.out.println("Error Write file: " + username);
                }
            }

        }
        photoService.save(photo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
