package com.tmk.ecom.product_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private BigDecimal price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer categoryId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String categoryName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer brandId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String brandName;
}
