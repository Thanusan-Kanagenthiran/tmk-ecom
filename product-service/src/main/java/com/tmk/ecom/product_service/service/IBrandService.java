package com.tmk.ecom.product_service.service;

import com.tmk.ecom.product_service.dto.BrandRequestDto;
import com.tmk.ecom.product_service.dto.BrandResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface IBrandService {

    void createBrand(@Valid BrandRequestDto category);

    List<BrandResponseDto> getAllBrands();

    BrandResponseDto getBrandWithProducts(Integer id);

    void updateBrand(Integer id, @Valid BrandRequestDto category);

    void deleteBrand(Integer id);

}
