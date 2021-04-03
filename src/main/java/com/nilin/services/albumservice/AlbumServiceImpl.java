package com.nilin.services.albumservice;

import com.nilin.exception.BusinessException;
import com.nilin.model.Album;
import com.nilin.repositories.albumrepository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository repository;

    public void save(Album album) {
        repository.save(album);
    }

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

    @Override
    public Album findAllById(Long id) {
        return repository.findAllById(id);
    }

    @Override
    public List<Album> findAllAlbums() {
        List<Album> albums = repository.findAll();
        if (albums.isEmpty()) {
            // I'm back 206 so I can print a message
            throw new BusinessException(404, "There is nothing to return");
        }
        return repository.findAll();
    }

    @Override
    public void deleteAllAlbums() {
        repository.deleteAll();
    }

    @Override
    public void deleteAlbumById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateAlbum(Album album) {
        save(album);
    }

}
