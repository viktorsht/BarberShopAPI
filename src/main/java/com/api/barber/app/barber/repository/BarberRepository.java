package com.api.barber.app.barber.repository;

import com.api.barber.app.barber.entity.BarberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<BarberEntity, Integer> {
}
