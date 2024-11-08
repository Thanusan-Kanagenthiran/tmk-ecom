package com.tmk.ecom.product_service.service.impl;

import com.tmk.ecom.product_service.dto.CategoryRequestDto;
import com.tmk.ecom.product_service.dto.CategoryResponseDto;
import com.tmk.ecom.product_service.exception.ResourceAlreadyExistException;
import com.tmk.ecom.product_service.exception.ResourceNotFoundException;
import com.tmk.ecom.product_service.mapper.CategoryMapper;
import com.tmk.ecom.product_service.model.Category;
import com.tmk.ecom.product_service.repository.CategoryRepository;
import com.tmk.ecom.product_service.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryRequestDto categoryDto) {
        categoryRepository.findByName(categoryDto.getName())
                .ifPresent(existingCategory -> {
                    throw new ResourceAlreadyExistException("Category", "name");
                });
        categoryRepository.save(CategoryMapper.toCategory(categoryDto));
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponseDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getCategoryWithProducts(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id.toString()));
        return CategoryMapper.toCategoryResponseDtoWithProducts(category);
    }

    @Override
    public void updateCategory(Integer id, CategoryRequestDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id.toString()));

        if (!existingCategory.getName().equals(categoryDto.getName())) {
            categoryRepository.findByName(categoryDto.getName())
                    .ifPresent(existing -> {
                        throw new ResourceAlreadyExistException("Category", "name");
                    });
        }
        existingCategory.setName(categoryDto.getName());
        categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id.toString()));
        categoryRepository.delete(category);
    }

}
