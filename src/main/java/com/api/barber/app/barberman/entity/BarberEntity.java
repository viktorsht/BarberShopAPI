package com.api.barber.app.barberman.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "barber")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BarberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updateAt;

    public BarberEntity(String name, String phone, String password, Instant createdAt, Instant updateAt){
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

}
