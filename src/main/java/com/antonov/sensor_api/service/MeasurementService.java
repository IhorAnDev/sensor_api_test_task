package com.antonov.sensor_api.service;

import com.antonov.sensor_api.dto.MeasurementDTO;
import com.antonov.sensor_api.entity.Measurement;
import com.antonov.sensor_api.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;


    @Transactional
    public void addMeasurement(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasurementData(LocalDateTime.now());
    }

    public List<Measurement> getAll() {
        return measurementRepository.findAll();
    }

    public Long getAllRainy() {
        return measurementRepository.findAll().stream().filter(Measurement::getIsRaining).count();
    }
}
