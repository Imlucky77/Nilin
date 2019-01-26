package com.nilin.services.albumservice;

import com.nilin.model.Album;

public interface AlbumService {

    Album findByName(String name);
    void createAlbum(Album album);
    boolean isAlbumExist(Album album);
}
