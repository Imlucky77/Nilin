package com.nilin.services.profileservice;

import com.nilin.model.Profile;
import com.nilin.repositories.profilerepository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository repository;

    @Override
    public void save(Profile profile) {
        repository.save(profile);
    }

    @Override
    public Profile findByFirstName(String name) {
        return repository.findByFirstName(name);
    }

    @Override
    public List<Profile> findAll() {
        return repository.findAll();
    }

    @Override
    public Profile findAllById(Long id) {
        return repository.findAllById(id);
    }

    @Override
    public void updateProfile(Profile profile) {
        save(profile);
    }

    @Override
    public void deleteProfileById(Long id) {
        repository.deleteById(id);

    }
}
