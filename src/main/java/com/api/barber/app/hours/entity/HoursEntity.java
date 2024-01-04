package com.api.barber.app.hours.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name="hours")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class HoursEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", columnDefinition = "int")
        private int id;
        @Column(name = "hour_time")
        private LocalTime time;
        @Column(name = "status")
        private Boolean status;

}

