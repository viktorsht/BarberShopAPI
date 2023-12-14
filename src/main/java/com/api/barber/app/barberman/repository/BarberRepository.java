package com.api.barber.app.barberman.repository;

import com.api.barber.app.barberman.entity.BarberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<BarberEntity, Integer> {
}
