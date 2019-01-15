package com.nilin.repositories.userrepository;

import com.nilin.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findAllById(Long id);

    Users findByName(String name);

}
