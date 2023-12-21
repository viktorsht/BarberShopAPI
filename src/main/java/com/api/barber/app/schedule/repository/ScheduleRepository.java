package com.api.barber.app.schedule.repository;

import com.api.barber.app.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
            "FROM ScheduleEntity s " +
            "WHERE s.scheduledDay = :scheduledDateTimeString")
    boolean existsByScheduledDateTimeString(@Param("scheduledDateTimeString") String scheduledDateTimeString);

    /*@Query("SELECT s.id FROM ScheduleEntity s " +
            "WHERE s.client.id = :clientId " +
            "ORDER BY s.scheduled_time DESC")
    int existsByScheduleClient(@Param("clientId") int clientId);*/

}
