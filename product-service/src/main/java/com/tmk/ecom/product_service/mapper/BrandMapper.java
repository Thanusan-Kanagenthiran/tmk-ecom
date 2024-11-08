package com.tmk.ecom.product_service.mapper;

import com.tmk.ecom.product_service.dto.BrandRequestDto;
import com.tmk.ecom.product_service.dto.BrandResponseDto;
import com.tmk.ecom.product_service.model.Brand;

public class BrandMapper {

    public static Brand toBrand(BrandRequestDto brandRequestDto) {
        Brand brand = new Brand();
        brand.setName(brandRequestDto.getName());
        return brand;
    }

    public static BrandResponseDto toBrandResponseDto(Brand brand) {
        return BrandResponseDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .totalProducts(brand.getProducts().size())
                .build();
    }

    public static BrandResponseDto toBrandResponseDtoWithProducts(Brand brand) {
        return BrandResponseDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .totalProducts(brand.getProducts().size())
                .products(brand.getProducts().stream().map(ProductMapper::toProductResponseDtoForBrand).toList())
                .build();
    }

}
