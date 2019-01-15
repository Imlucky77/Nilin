package com.nilin.services.photoservice;

import com.nilin.model.Photo;

public interface PhotoService {

    void save(Photo photo);

    Photo findByName(String text);

    Photo findFirstByName(String text);
}
