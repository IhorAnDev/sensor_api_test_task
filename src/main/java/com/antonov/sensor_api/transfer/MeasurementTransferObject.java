package com.antonov.sensor_api.transfer;

import com.antonov.sensor_api.dto.MeasurementDTO;
import com.antonov.sensor_api.entity.Measurement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeasurementTransferObject {

    private final ModelMapper modelMapper;

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
