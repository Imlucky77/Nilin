package com.nilin.repositories.clientrepository;

import com.nilin.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByFirstName(String name);

    Client findAllById(Long id);
}
