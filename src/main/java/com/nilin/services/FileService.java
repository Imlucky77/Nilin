package com.nilin.services;

import com.nilin.model.FileModel;

import java.util.List;
import java.util.Optional;

public interface FileService {

    FileModel findByName(String name);

    List<FileModel> findAll();

    Optional<FileModel> findById(Long id);
}
