package com.api.barber.app.hoursActive.repositoty;

import com.api.barber.app.hoursActive.entity.HoursActiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoursActiveRepository extends JpaRepository<HoursActiveEntity, Integer> {
    public List<HoursActiveEntity> findByDay(int day);
}
