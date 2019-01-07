package com.nilin.services.profileservice;

import com.nilin.model.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Profile findByFirstName(String name);

    List<Profile> findAll();

    Optional<Profile> findById(Long id);
}
