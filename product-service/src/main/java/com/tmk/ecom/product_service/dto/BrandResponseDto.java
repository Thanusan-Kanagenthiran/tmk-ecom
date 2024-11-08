package com.tmk.ecom.product_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BrandResponseDto {
    private Integer id;
    private String name;
    private Integer totalProducts;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProductResponseDto> products;
}
