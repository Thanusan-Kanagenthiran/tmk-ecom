package com.tmk.ecom.product_service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BrandResponseDto {
    private Integer id;
    private String name;
    private Integer totalProducts;
    private List<CategoryResponseDto> categories;
    private List<ProductResponseDto> products;
}
