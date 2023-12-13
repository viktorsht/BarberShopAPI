package com.api.barber.app.daysActives.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="days")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class DaysEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;

}
