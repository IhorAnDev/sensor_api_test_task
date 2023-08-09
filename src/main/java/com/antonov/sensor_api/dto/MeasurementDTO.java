package com.antonov.sensor_api.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class MeasurementDTO {

    @NotNull
    @Min(value = -100, message = "min value should not be less than -100")
    @Max(value = 100, message = "max value should not be greater than 100")
    private Double value;

    @NotNull
    private Boolean isRaining;

    @NotNull
    private SensorDTO sensor;
}
