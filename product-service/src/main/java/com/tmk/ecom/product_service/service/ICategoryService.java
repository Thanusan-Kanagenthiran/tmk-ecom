package com.tmk.ecom.product_service.service;

import com.tmk.ecom.product_service.dto.CategoryRequestDto;
import com.tmk.ecom.product_service.dto.CategoryResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ICategoryService {

    void createCategory(@Valid CategoryRequestDto category);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryWithProducts(Integer id);

    void updateCategory(Integer id, @Valid CategoryRequestDto category);

    void deleteCategory(Integer id);
}
