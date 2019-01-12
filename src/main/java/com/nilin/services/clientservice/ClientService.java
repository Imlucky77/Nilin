package com.nilin.services.clientservice;

import com.nilin.model.Client;

import java.util.List;

public interface ClientService {

    void save(Client client);

    Client findByFirstName(String name);

    List<Client> findAll();

    Client findAllById(Long id);

    void updateClient(Client client);
}
