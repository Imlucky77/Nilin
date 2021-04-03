package com.nilin.repositories.albumrepository;

import com.nilin.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    Album findByName(String name);

    Album findAllById(Long id);


}
