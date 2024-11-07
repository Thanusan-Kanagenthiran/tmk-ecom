package com.tmk.ecom.product_service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ProductResponseDto {
    private Integer id;
    private String name;
    private String description;
    private Integer availableQuantity;
    private Date releaseDate;
    private BigDecimal price;
    private Integer categoryId;
    private String categoryName;
    private Integer brandId;
    private String brandName;
}
