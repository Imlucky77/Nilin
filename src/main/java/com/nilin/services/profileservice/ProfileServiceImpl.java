package com.nilin.services.profileservice;

import com.nilin.model.Profile;
import com.nilin.repositories.profilerepository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public List<Profile> findAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public void updateProfile(Profile profile) {
        save(profile);
    }

    @Override
    public Profile findAllById(Long id) {

        return profileRepository.findAllById(id);
    }
}
