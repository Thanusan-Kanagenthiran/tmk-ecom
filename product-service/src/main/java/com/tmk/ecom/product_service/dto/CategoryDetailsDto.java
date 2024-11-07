package com.tmk.ecom.product_service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDetailsDto {
    private Integer id;
    private String name;
    private String description;
    private Integer totalProducts;
    private List<ProductDto> products;
}
