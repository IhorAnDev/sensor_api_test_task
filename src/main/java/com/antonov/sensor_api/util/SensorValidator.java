package com.antonov.sensor_api.util;

import com.antonov.sensor_api.dto.SensorDTO;
import com.antonov.sensor_api.entity.Sensor;
import com.antonov.sensor_api.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SensorValidator implements Validator {

    private final SensorRepository sensorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if (sensorRepository.findByName(sensorDTO.getName()).isPresent()) {
            errors.rejectValue("name", "", "this sensor has already registered");
        }
    }
}
