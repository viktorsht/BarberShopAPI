package com.api.barber.app.client.service;

import com.api.barber.app.client.dto.ClientDTO;
import com.api.barber.app.client.entity.ClientEntity;
import com.api.barber.app.client.repository.ClientRepository;
import com.api.barber.app.schedule.entity.ScheduleEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    final private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientEntity> listClientsService(){
        return clientRepository.findAll();
    }

    public Optional<ClientEntity> listClientById(int clientId){
        return clientRepository.findById(clientId);
    }

    public Optional<ClientEntity> searchClientByPhoneNumber(String phone){
        return clientRepository.findByPhone(phone);
    }

    public ClientEntity createClient(ClientDTO clientDTO){
        var entity = new ClientEntity(
                clientDTO.name(),
                clientDTO.phone(),
                Instant.now(),
                Instant.now()
        );
        return clientRepository.save(entity);
    }

    public void updateClient(int clientId, ClientDTO clientDTO){
        var clientEntity = clientRepository.findById(clientId);
        if(clientEntity.isPresent()){
            var client = clientEntity.get();
            if(clientDTO.name() != null){
                client.setName(clientDTO.name());
            }
            if(clientDTO.phone() != null){
                client.setPhone(clientDTO.phone());
            }
            clientRepository.save(client);
        }
    }
}
