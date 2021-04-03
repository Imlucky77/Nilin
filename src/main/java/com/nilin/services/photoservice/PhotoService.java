package com.nilin.services.photoservice;

import com.nilin.model.Photo;

import java.util.List;

public interface PhotoService {

    Photo findById(String fileId);

    List<Photo> findAll();

    Photo findAllById(Long id);

    void deletePhotoById(Long id);
}
