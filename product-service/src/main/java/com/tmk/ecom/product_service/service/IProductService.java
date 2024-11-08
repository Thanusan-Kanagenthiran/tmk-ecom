package com.tmk.ecom.product_service.service;

import com.tmk.ecom.product_service.dto.ProductRequestDto;
import com.tmk.ecom.product_service.dto.ProductResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface IProductService {

    void createProduct(@Valid ProductRequestDto brand);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProductWithDetails(Integer id);

    void updateProduct(Integer id, @Valid ProductRequestDto product);

    void deleteProduct(Integer id);
}
