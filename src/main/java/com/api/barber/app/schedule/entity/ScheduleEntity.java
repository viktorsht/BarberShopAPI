package com.api.barber.app.schedule.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    private int id;

    @Column(name = "scheduled_day")
    private String scheduledDay;

    @Column(name = "service_id")
    private int service;

    @Column(name = "payment_method_id")
    private int paymentMethod;

    @Column(name = "client_id")
    private int client;

    @Column(name = "barber_id")
    private int barber;

    @CreationTimestamp
    @Column(name = "scheduled_time", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime scheduledTime;

    public ScheduleEntity(String scheduledDay, int service, int payment, int client, int barber, LocalDateTime scheduledTime) {
        this.scheduledDay = scheduledDay;
        this.service = service;
        this.paymentMethod = payment;
        this.client = client;
        this.barber = barber;
        this.scheduledTime = scheduledTime;
    }
}
