package com.nilin.repositories.profilerepository;

import com.nilin.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByFirstName(String name);

    Profile findAllById(Long id);
}
