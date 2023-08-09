package com.antonov.sensor_api.util;

import com.antonov.sensor_api.dto.MeasurementDTO;
import com.antonov.sensor_api.repository.MeasurementRepository;
import com.antonov.sensor_api.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MeasurementValidator implements Validator {


    private final SensorRepository sensorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        if (sensorRepository.findByName(measurementDTO.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor", "", "There is no sensor with this name");
        }
    }
}
