package com.nilin.services.profileservice;

import com.nilin.model.Profile;

import java.util.List;

public interface ProfileService {

    void save(Profile profile);

    Profile findByFirstName(String name);

    List<Profile> findAll();

    Profile findAllById(Long id);

    void updateProfile(Profile profile);
}
