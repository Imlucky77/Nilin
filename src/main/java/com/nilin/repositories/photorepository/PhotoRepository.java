package com.nilin.repositories.photorepository;

import com.nilin.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Photo findAllById(Long id);

}
