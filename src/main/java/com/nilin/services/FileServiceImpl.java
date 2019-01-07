package com.nilin.services;

import com.nilin.model.FileModel;
import com.nilin.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository repository;

    @Override
    public FileModel findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<FileModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<FileModel> findById(Long id) {
        return repository.findById(id);
    }
}
