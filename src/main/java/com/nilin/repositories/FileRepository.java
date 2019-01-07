package com.nilin.repositories;

import com.nilin.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long> {
    FileModel findByName(String name);
}
