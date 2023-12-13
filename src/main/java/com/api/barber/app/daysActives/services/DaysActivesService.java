package com.api.barber.app.daysActives.services;
import com.api.barber.app.daysActives.dto.DaysActivesDTO;
import com.api.barber.app.daysActives.entity.DaysEntity;
import com.api.barber.app.daysActives.repositories.DaysActivesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DaysActivesService {
    private final DaysActivesRepository daysActivesRepository;

    public DaysActivesService(DaysActivesRepository daysActivesRepository) {
        this.daysActivesRepository = daysActivesRepository;
    }

    public List<DaysEntity> listForDaysActives(){
        return daysActivesRepository.findAll();
    }
    public Optional<DaysEntity> getDaysActivesById(int id){

        return daysActivesRepository.findById(id);
    }

    public void updateDay(int dayId, DaysActivesDTO daysActivesDTO){
        var daysEntity = daysActivesRepository.findById(dayId);
        if(daysEntity.isPresent()) {
            var day = daysEntity.get();
            if(daysActivesDTO.status() != null){
                day.setStatus(daysActivesDTO.status());
                daysActivesRepository.save(day);
            }
        }
    }
}
