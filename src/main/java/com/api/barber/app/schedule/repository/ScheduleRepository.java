package com.api.barber.app.schedule.repository;

import com.api.barber.app.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
            "FROM ScheduleEntity s " +
            "WHERE s.scheduledDay = :scheduledDateTimeString " +
            "AND s.barber = :barberId")
    boolean existsByScheduledDateTimeStringAndBarberId(
            @Param("scheduledDateTimeString") String scheduledDateTimeString,
            @Param("barberId") int barberId
    );

    List<ScheduleEntity> findScheduleByClient(int clientId);
    List<ScheduleEntity> findScheduleByBarber(int barberId);
    @Query(value = "SELECT * FROM schedule s " +
            "WHERE s.barber_id = :barberId " +
            "AND STR_TO_DATE(s.scheduled_day, '%d-%m-%Y %H:%i:%s') >= CURRENT_DATE " +
            "ORDER BY s.scheduled_day LIMIT 1", nativeQuery = true)
    Optional<ScheduleEntity> findNearestScheduleByBarber(@Param("barberId") int barberId);
    @Query(value = "SELECT * FROM schedule s " +
            "WHERE s.client_id = :clientId " +
            "AND STR_TO_DATE(s.scheduled_day, '%d-%m-%Y %H:%i:%s') >= CURRENT_DATE " +
            "ORDER BY s.scheduled_day LIMIT 1", nativeQuery = true)
    Optional<ScheduleEntity> findNearestScheduleByClient(@Param("clientId") int clientId);



}
