package com.nilin.services.profileservice;

import com.nilin.model.Profile;

import java.util.List;

public interface ProfileService {

    void save(Profile profile);

    List<Profile> findAllProfiles();

    void updateProfile(Profile profile);
    Profile findAllById(Long id);

}
