package com.nilin.web.albumcontroller;

import com.nilin.exception.BusinessException;
import com.nilin.model.Album;
import com.nilin.services.albumservice.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

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
            LocalDate today = LocalDate.now();
            album.setCreated(today);
            service.createAlbum(album);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }

    @GetMapping(value = "/albums")
    public ResponseEntity<?> getAllAlbums() {
        try {
            List<Album> allAlbums = service.findAllAlbums();
            return new ResponseEntity<>(allAlbums, HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }

    // -------------------Retrieve Single Album------------------------------------------
    @ApiOperation(value = "Get a album by Id")
    @GetMapping(value = "/albums/{albumId}")
    public ResponseEntity<?> getUser(
            @ApiParam(value = "Album id from which album object will retrieve", required = true)
            @PathVariable("albumId") long id) {
        Album album = service.findAllById(id);
        if (album == null) {
            return new ResponseEntity<>("Album with id " + id
                    + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    // ------------------- Delete All Album -----------------------------
    @ApiOperation(value = "Delete all albums")
    @DeleteMapping(value = "/albums")
    public ResponseEntity<Album> deleteAllAlbums() {
        service.deleteAllAlbums();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete a Album-----------------------------------------
    @ApiOperation(value = "Delete a album by Id")
    @DeleteMapping(value = "/albums/{albumId}")
    public ResponseEntity<?> deleteAlbumById(
            @ApiParam(value = "Album Id from which album object will delete from database table", required = true)
            @PathVariable("albumId") long id
    ) {

        Album album = service.findAllById(id);
        if (album == null) {
            return new ResponseEntity<>("Unable to delete. User with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }
        service.deleteAlbumById(id);
        return new ResponseEntity<Album>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Update a Album ------------------------------------------------
    @ApiOperation(value = "Update a album by Id")
    @PutMapping(value = "/albums/{albumId}")
    public ResponseEntity<?> updateAlbum(
            @ApiParam(value = "Album Id to update album object", required = true) @PathVariable("albumId") long id,
            @ApiParam(value = "Update album object", required = true) @Valid @RequestBody Album album) {

        Album currentAlbum = service.findAllById(id);

        if (currentAlbum == null) {
            return new ResponseEntity<>("Unable to update. Album with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }

        currentAlbum.setName(album.getName());
        currentAlbum.setTitle(album.getTitle());
        currentAlbum.setDescription(album.getDescription());
        LocalDate today = LocalDate.now();
        currentAlbum.setCreated(today);

        service.updateAlbum(currentAlbum);
        return new ResponseEntity<>(currentAlbum, HttpStatus.OK);
    }
}
