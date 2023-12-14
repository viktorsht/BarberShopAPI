package com.api.barber.app.servicesActives.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServicesActiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "duration_minutes")
    private int durationMinutes;
    @Column(name = "price", columnDefinition = "DECIMAL")
    private BigDecimal price;

    public ServicesActiveEntity(String name, int durationMinutes, BigDecimal price){
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.price = price;
    }
}
