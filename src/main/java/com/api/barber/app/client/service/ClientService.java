package com.api.barber.app.client.service;

import com.api.barber.app.client.repository.ClientRepository;

public class ClientService {
    final private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
