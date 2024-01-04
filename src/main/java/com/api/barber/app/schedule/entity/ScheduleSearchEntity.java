package com.api.barber.app.schedule.entity;

import com.api.barber.app.barberman.entity.BarberEntity;
import com.api.barber.app.client.entity.ClientEntity;
import com.api.barber.app.paymentMethods.entity.PaymentMethodsEntity;
import com.api.barber.app.servicesActives.entity.ServicesActiveEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ScheduleSearchEntity {
    private int id;
    private String scheduledDay;
    private ServicesActiveEntity service;
    private PaymentMethodsEntity paymentMethod;
    private int client;
    private int barber;
    private LocalDateTime scheduledTime;
}
