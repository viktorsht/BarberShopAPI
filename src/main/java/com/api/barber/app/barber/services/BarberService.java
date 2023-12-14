package com.api.barber.app.barber.services;

import com.api.barber.app.barber.dto.BarberDTO;
import com.api.barber.app.barber.entity.BarberEntity;
import com.api.barber.app.barber.repository.BarberRepository;
import com.api.barber.app.client.dto.ClientDTO;
import com.api.barber.app.client.entity.ClientEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
@Service
public class BarberService {
    final private BarberRepository barberRepository;

    public BarberService(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    public List<BarberEntity> listBarbersService(){
        return barberRepository.findAll();
    }

    public BarberEntity createBarber(BarberDTO clientDTO){
        var entity = new BarberEntity(
                clientDTO.name(),
                clientDTO.phone(),
                Instant.now(),
                Instant.now()
        );
        return barberRepository.save(entity);
    }

    public void updateBarber(int clientId, BarberDTO barberDTO){
        var barberEntity = barberRepository.findById(clientId);
        if(barberEntity.isPresent()){
            var client = barberEntity.get();
            if(barberDTO.name() != null){
                client.setName(barberDTO.name());
            }
            if(barberDTO.phone() != null){
                client.setPhone(barberDTO.phone());
            }
            barberRepository.save(client);
        }
    }
}
