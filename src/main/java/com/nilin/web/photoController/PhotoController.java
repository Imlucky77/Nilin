package com.nilin.web.photoController;

import com.nilin.model.Photo;
import com.nilin.services.photoservice.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Api(value = "Photo Management System", description = "Operations pertaining to photo in Photo Management System")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @ApiOperation(value = "Add a photo", response = List.class)
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public ResponseEntity<?> handleFileUpload(
            @ApiParam(value = "Photo object store in database table", required = true) @Valid @RequestBody Photo photo,
            HttpServletRequest request, @RequestParam("file") MultipartFile[] fileUpload) {

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
