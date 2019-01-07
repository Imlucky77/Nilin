package com.nilin.services.profileservice;

import com.nilin.model.Profile;
import com.nilin.repositories.profilerepository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository repository;

    @Override
    public Profile findByFirstName(String name) {
        return repository.findByFirstName(name);
    }

    @Override
    public List<Profile> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return repository.findById(id);
    }
}
