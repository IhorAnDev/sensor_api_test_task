package com.antonov.sensor_api.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MeasurementErrorResponse {

    private String message;
    private long timestamp;

}
