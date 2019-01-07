package com.nilin.repositories.profilerepository;

import com.nilin.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByFirstName(String name);
}
