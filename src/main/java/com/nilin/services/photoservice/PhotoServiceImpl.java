package com.nilin.services.photoservice;

import com.nilin.model.Photo;
import com.nilin.repositories.photorepository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public void save(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    public Photo findByName(String text) {
        return photoRepository.findByName(text);
    }

    @Override
    public Photo findFirstByName(String text) {
        return photoRepository.findFirstByName(text);
    }
}
