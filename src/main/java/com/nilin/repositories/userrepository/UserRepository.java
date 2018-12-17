package com.nilin.repositories.userrepository;

import com.nilin.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{

    User findAllById(Long id);

}
