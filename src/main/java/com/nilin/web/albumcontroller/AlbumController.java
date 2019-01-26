package com.nilin.web.albumcontroller;

import com.nilin.model.Album;
import com.nilin.services.albumservice.AlbumService;
import com.nilin.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService service;

    @PostMapping(value = "/albums")
    public ResponseEntity<?> createAlbum(@RequestBody Album album) {

        try {
            service.createAlbum(album);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }
}
