package com.api.barber.app.daysActives.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.barber.app.daysActives.models.DaysActivesModel;

public interface DaysActivesRepository extends JpaRepository<DaysActivesModel, Integer>{
    
}
