package com.api.barber.app.hoursActive.entity;

import jakarta.persistence.*;
import lombok.*;

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
        @Column(name = "name")
        private String name;
        @Column(name = "status")
        private Boolean status;

}

