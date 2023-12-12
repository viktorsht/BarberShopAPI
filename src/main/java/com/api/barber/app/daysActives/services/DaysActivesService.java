package com.api.barber.app.daysActives.services;
import com.api.barber.app.daysActives.models.DaysActivesModel;
import com.api.barber.app.daysActives.repositories.DaysActivesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaysActivesService {
    private final DaysActivesRepository daysActivesRepository;

    public DaysActivesService(DaysActivesRepository daysActivesRepository) {
        this.daysActivesRepository = daysActivesRepository;
    }

    public List<DaysActivesModel> listForDaysActives(){
        return daysActivesRepository.findAll();
    }
}
