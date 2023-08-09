package com.antonov.sensor_api.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "measurement")
@Getter
@Setter
@ToString
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private Double value;

    @Column(name = "is_raining")
    private Boolean isRaining;

    @Column(name = "measurement_data")
    private LocalDateTime measurementData;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;


}
