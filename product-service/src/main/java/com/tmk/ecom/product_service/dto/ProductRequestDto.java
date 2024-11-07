package com.tmk.ecom.product_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductRequestDto {

    @NotBlank(message = "Product name is required")
    @Size(min = 3 , max = 50, message = "Product name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Product description is required")
    @Size(min = 3 , max = 100, message = "Category description must be between 3 and 100 characters")
    private String description;

    @PositiveOrZero(message = "Available quantity must be greater than or equal to 0")
    @NotNull(message = "Available quantity is required")
    private Integer availableQuantity;

    // TODO:: Validate release date
    @DateTimeFormat(pattern = "yy-MM-dd")
    @NotNull(message = "Release date is required")
    private Date releaseDate;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Category is required")
    private Integer categoryId;

    @NotNull(message = "Brand is required")
    private Integer brandId;

}
