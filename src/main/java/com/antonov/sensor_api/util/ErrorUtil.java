package com.antonov.sensor_api.util;

import org.springframework.validation.BindingResult;

public class ErrorUtil {
    public static void returnErrors(BindingResult bindingResult){
        StringBuilder errorMsg = new StringBuilder();

        bindingResult.getFieldErrors().forEach(err -> {
            errorMsg.append(err.getField())
                    .append(" - ")
                    .append(err.getDefaultMessage())
                    .append("; ");
        });
        throw new MeasurementException(errorMsg.toString());
    }
}
