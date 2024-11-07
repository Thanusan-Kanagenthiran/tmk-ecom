package com.tmk.ecom.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    @NotBlank(message = "Product name is required")
    @Size(min = 3 , max = 50, message = "Product name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Product description is required")
    @Size(min = 3 , max = 100, message = "Category description must be between 3 and 100 characters")
    private String description;

    @Positive(message = "Available quantity must be greater than 0")
    @NotBlank(message = "Available quantity is required")
    private double availableQuantity;

    @NotBlank(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "Category is required")
    private Integer categoryId;

}
