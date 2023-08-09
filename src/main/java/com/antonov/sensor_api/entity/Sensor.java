package com.antonov.sensor_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "sensor")
public class Sensor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public Sensor(String name) {
        this.name = name;
    }
}
