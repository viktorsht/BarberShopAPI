package com.api.barber.app.barberman.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BarberResponseEntity {
    private int id;
    private String name;
    private String phone;
    private Instant createdAt;
    private Instant updateAt;

    public BarberResponseEntity(BarberEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.phone = entity.getPhone();
        this.createdAt = entity.getCreatedAt();
        this.updateAt = entity.getUpdateAt();
    }

}
