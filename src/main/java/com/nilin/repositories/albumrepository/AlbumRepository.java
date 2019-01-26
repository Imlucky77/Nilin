package com.nilin.repositories.albumrepository;

import com.nilin.model.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {

    Album findByName(String name);
}
