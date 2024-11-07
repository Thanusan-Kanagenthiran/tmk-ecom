package com.tmk.ecom.product_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotBlank(message = "Category name is required")
    @Size(min = 3 , max = 50, message = "Category name must be between 3 and 50 characters")
    private String name;

}
