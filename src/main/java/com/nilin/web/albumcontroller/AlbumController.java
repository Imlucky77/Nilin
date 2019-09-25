package com.nilin.web.albumcontroller;

import com.nilin.model.Album;
import com.nilin.services.albumservice.AlbumService;
import com.nilin.util.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "Album Management System", description = "Operations pertaining to album in Album Management System")
public class AlbumController {

    @Autowired
    private AlbumService service;

    @ApiOperation(value = "Add an album")
    @PostMapping(value = "/albums")
    public ResponseEntity<?> createAlbum(
            @ApiParam(value = "Album object store in database table", required = true) @Valid
            @RequestBody Album album) {

        try {
            service.createAlbum(album);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }
}
