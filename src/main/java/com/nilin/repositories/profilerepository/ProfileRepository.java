package com.nilin.repositories.profilerepository;

import com.nilin.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
