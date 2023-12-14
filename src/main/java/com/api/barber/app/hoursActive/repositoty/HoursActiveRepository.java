package com.api.barber.app.hoursActive.repositoty;

import com.api.barber.app.hoursActive.entity.HoursActiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoursActiveRepository extends JpaRepository<HoursActiveEntity, Integer> {
}
