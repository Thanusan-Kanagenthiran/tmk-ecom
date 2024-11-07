package com.tmk.ecom.product_service.mapper;

import com.tmk.ecom.product_service.dto.ProductRequestDto;
import com.tmk.ecom.product_service.dto.ProductResponseDto;
import com.tmk.ecom.product_service.model.Brand;
import com.tmk.ecom.product_service.model.Category;
import com.tmk.ecom.product_service.model.Product;

import java.util.List;

public class ProductMapper {

    public static Product toProduct(
            ProductRequestDto productRequestDto, Category category, Brand brand) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setAvailableQuantity(productRequestDto.getAvailableQuantity());
        product.setPrice(productRequestDto.getPrice());
        product.setReleaseDate(productRequestDto.getReleaseDate());
        product.setCategory(category);
        product.setBrand(brand);
        return product;
    }

    public static ProductResponseDto toProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .releaseDate(product.getReleaseDate())
                .build();
    }

    public static ProductResponseDto toProductResponseDtoWithDetails(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .releaseDate(product.getReleaseDate())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .brandId(product.getBrand().getId())
                .brandName(product.getCategory().getName())
                .build();
    }

}