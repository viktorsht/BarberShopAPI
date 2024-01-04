package com.api.barber.app.hours.repositoty;

import com.api.barber.app.hours.entity.HoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoursRepository extends JpaRepository<HoursEntity, Integer> {
}
