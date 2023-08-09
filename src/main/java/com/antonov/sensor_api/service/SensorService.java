package com.antonov.sensor_api.service;

import com.antonov.sensor_api.entity.Sensor;
import com.antonov.sensor_api.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Transactional
    public void createSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }
}
