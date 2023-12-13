package com.api.barber.app.daysActives.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.barber.app.daysActives.entity.DaysEntity;

public interface DaysActivesRepository extends JpaRepository<DaysEntity, Integer>{
    
}
