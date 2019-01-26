package com.nilin.services.albumservice;

import com.nilin.model.Album;
import com.nilin.repositories.albumrepository.AlbumRepository;
import com.nilin.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository repository;


    @Override
    public Album findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void createAlbum(Album album) {
        if (isAlbumExist(album)) {
            throw new BusinessException(400, "Unable to create. A Album with name " + album.getName() + " already exist.");
        }
        repository.save(album);
    }

    @Override
    public boolean isAlbumExist(Album album) {
        return findByName(album.getName()) != null;
    }

}
