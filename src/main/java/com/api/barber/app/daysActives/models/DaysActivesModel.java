package com.api.barber.app.daysActives.models;

import jakarta.persistence.*;
import lombok.*;

@Entity()
@Table(name="days")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class DaysActivesModel {
    //public DaysModel() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    private int id;
    private String name;
    private boolean status;

}
