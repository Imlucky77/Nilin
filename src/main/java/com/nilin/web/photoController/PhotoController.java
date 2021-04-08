package com.nilin.web.photoController;

import com.nilin.exception.BusinessException;
import com.nilin.model.Photo;
import com.nilin.model.Profile;
import com.nilin.repositories.photorepository.PhotoRepository;
import com.nilin.services.photoservice.PhotoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@Api(value = "Photo Management System", description = "Operations pertaining to photo in Photo Management System")
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PhotoService service;


    @PostMapping("/photos")
    public Photo createPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        Photo img = new Photo(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        final Photo savedImage = photoRepository.save(img);
        return savedImage;
    }

    // -------------------Retrieve All Photos---------------------------------------------
    @ApiOperation(value = "View a list of available profiles", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/photos")
    public ResponseEntity<List<Photo>> getAllPhotos() {
        List<Photo> photos = service.findAll();
        if (photos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    // -------------------Retrieve Single Photo------------------------------------------
    @ApiOperation(value = "Get an photos by Id")
    @GetMapping(value = "/photos/{photoId}")
    @Transactional
    public ResponseEntity<?> getPhotoById(@ApiParam(value = "Photo id from which photo object will retrieve", required = true)
                                          @PathVariable("photoId") long id) {
        Photo photo = service.findAllById(id);
        if (photo == null) {
            return new ResponseEntity<>(new BusinessException("Photo with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    // ------------------- Delete a Photo -----------------------------------------
    @ApiOperation(value = "Delete a photo by Id")
    @DeleteMapping(value = "/photos/{photoId}")
    @Transactional
    public ResponseEntity<?> deletePhotoById(
            @ApiParam(value = "Photos Id from which photo object will delete from database table", required = true)
            @PathVariable("photoId") long id
    ) {

        Photo photo = service.findAllById(id);
        if (photo == null) {
            return new ResponseEntity<>("Unable to delete. Profile with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }
        service.deletePhotoById(id);
        return new ResponseEntity<Profile>(HttpStatus.NO_CONTENT);
    }

}
