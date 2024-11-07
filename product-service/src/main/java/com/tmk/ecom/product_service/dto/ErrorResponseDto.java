package com.tmk.ecom.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;
    private String message;
    private String statusCode;
    private String timestamp;

}
