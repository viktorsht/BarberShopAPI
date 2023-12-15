package com.api.barber.app.schedule.entity;

import com.api.barber.app.barberman.entity.BarberEntity;
import com.api.barber.app.client.entity.ClientEntity;
import com.api.barber.app.daysActives.entity.DaysEntity;
import com.api.barber.app.hoursActive.entity.HoursActiveEntity;
import com.api.barber.app.paymentMethods.entity.PaymentMethodsEntity;
import com.api.barber.app.servicesActives.entity.ServicesActiveEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
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

    @ManyToOne
    @JoinColumn(name = "day_id")
    private int day;

    @ManyToOne
    @JoinColumn(name = "hour_id")
    private int activeHour;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private int service;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private int paymentMethod;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private int client;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private int barber;

    @CreationTimestamp
    @Column(name = "scheduled_time", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant scheduledTime;

    public ScheduleEntity(int day, int hours, int service, int payment, int client, int barber) {
        this.day = day;
        this.activeHour = hours;
        this.service = service;
        this.paymentMethod = payment;
        this.client = client;
        this.barber = barber;
    }
}
