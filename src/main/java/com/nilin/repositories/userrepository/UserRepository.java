package com.nilin.repositories.userrepository;

import com.nilin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findAllById(Long id);

    User findByUsername(String username);

}
