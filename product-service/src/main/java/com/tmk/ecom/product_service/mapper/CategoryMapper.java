package com.tmk.ecom.product_service.mapper;

import com.tmk.ecom.product_service.dto.CategoryRequestDto;
import com.tmk.ecom.product_service.dto.CategoryResponseDto;
import com.tmk.ecom.product_service.model.Category;

public class CategoryMapper {

    public static Category toCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }

    public static CategoryResponseDto toCategoryResponseDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .totalProducts(category.getProducts().size())
                .build();
    }

    public static CategoryResponseDto toCategoryResponseDtoWithProducts(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .totalProducts(category.getProducts().size())
                .products(category.getProducts().stream().map(ProductMapper::toProductResponseDto).toList())
                .build();
    }
}
