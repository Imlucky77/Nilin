package com.nilin.repositories.userrepository;

import com.nilin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findAllById(Long id);

    Optional<User> findByUsername(String username);


    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
   /* Boolean existsByFirstName(String firstName);
    Boolean existsByLastName(String lastName);
    Boolean existsByMobile(String mobile);
    Boolean existsByBirthday(LocalDate birthday);*/

}
