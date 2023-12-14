package com.api.barber.app.hoursActive.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name="active_hours")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class HoursActiveEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", columnDefinition = "int")
        private int id;
        @Column(name = "hour_time")
        private LocalTime time;
        @Column(name = "status")
        private Boolean status;

}

