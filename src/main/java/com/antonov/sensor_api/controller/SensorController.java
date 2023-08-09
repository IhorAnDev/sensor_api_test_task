package com.antonov.sensor_api.controller;


import com.antonov.sensor_api.dto.SensorDTO;
import com.antonov.sensor_api.entity.Sensor;
import com.antonov.sensor_api.service.SensorService;
import com.antonov.sensor_api.util.ErrorUtil;
import com.antonov.sensor_api.util.MeasurementErrorResponse;
import com.antonov.sensor_api.util.MeasurementException;
import com.antonov.sensor_api.util.SensorValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sensor")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtil.returnErrors(bindingResult);
        }
        sensorService.createSensor(converterToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Sensor converterToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
