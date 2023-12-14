package com.api.barber.app.barberman.services;

import com.api.barber.app.barberman.dto.BarberDTO;
import com.api.barber.app.barberman.entity.BarberEntity;
import com.api.barber.app.barberman.repository.BarberRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class BarberService {
    final private BarberRepository barberRepository;

    public BarberService(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    public List<BarberEntity> listBarbersService(){
        return barberRepository.findAll();
    }

    public Optional<BarberEntity> listBarberById(int barberId){
        return barberRepository.findById(barberId);
    }
    public BarberEntity createBarber(BarberDTO barberDTO){
        var entity = new BarberEntity(
                barberDTO.name(),
                barberDTO.phone(),
                barberDTO.password(),
                Instant.now(),
                Instant.now()
        );
        return barberRepository.save(entity);
    }

    public void updateBarber(int barberId, BarberDTO barberDTO){
        var barberEntity = barberRepository.findById(barberId);
        if(barberEntity.isPresent()){
            var barber = barberEntity.get();
            if(barberDTO.name() != null){
                barber.setName(barberDTO.name());
            }
            if(barberDTO.phone() != null){
                barber.setPhone(barberDTO.phone());
            }
            barberRepository.save(barber);
        }
    }

    public void deleteBarberById(int barberId){
        var barberEntity = barberRepository.existsById(barberId);
        if(barberEntity) {
            barberRepository.deleteById(barberId);
        }
    }
}
