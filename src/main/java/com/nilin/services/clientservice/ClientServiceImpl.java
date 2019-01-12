package com.nilin.services.clientservice;

import com.nilin.model.Client;
import com.nilin.repositories.clientrepository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public void save(Client client) {
        repository.save(client);
    }

    @Override
    public Client findByFirstName(String name) {
        return repository.findByFirstName(name);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Client findAllById(Long id) {
        return repository.findAllById(id);
    }

    @Override
    public void updateClient(Client client) {
        save(client);
    }
}
