package com.nilin.services.albumservice;

import com.nilin.model.Album;

import java.util.List;

public interface AlbumService {

    Album findByName(String name);

    void createAlbum(Album album);

    boolean isAlbumExist(Album album);

    Album findAllById(Long id);

    List<Album> findAllAlbums();

    void deleteAllAlbums();

    void deleteAlbumById(Long id);

    void updateAlbum(Album album);
}
