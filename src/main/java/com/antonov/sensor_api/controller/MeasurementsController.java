package com.antonov.sensor_api.controller;

import com.antonov.sensor_api.dto.MeasurementDTO;
import com.antonov.sensor_api.dto.MeasurementResponse;
import com.antonov.sensor_api.entity.Measurement;
import com.antonov.sensor_api.entity.Sensor;
import com.antonov.sensor_api.service.MeasurementService;
import com.antonov.sensor_api.transfer.MeasurementTransferObject;
import com.antonov.sensor_api.util.ErrorUtil;
import com.antonov.sensor_api.util.MeasurementErrorResponse;
import com.antonov.sensor_api.util.MeasurementException;
import com.antonov.sensor_api.util.MeasurementValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementsController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final MeasurementTransferObject measurementTransferObject;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> doMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                    BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtil.returnErrors(bindingResult);
        }
        measurementService.addMeasurement(this.measurementTransferObject.convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<MeasurementResponse> getAll() {
        MeasurementResponse measurementResponse = new MeasurementResponse(measurementService.getAll().stream()
                .map(this.measurementTransferObject::convertToMeasurementDTO).collect(Collectors.toList()));

        return ResponseEntity.ok(measurementResponse);
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Long> getRainyDays() {
        return ResponseEntity.ok(measurementService.getAllRainy());
    }

}
