package com.tmk.ecom.product_service.controller;

import com.tmk.ecom.product_service.dto.CategoryRequestDto;
import com.tmk.ecom.product_service.dto.CategoryResponseDto;
import com.tmk.ecom.product_service.mapper.CategoryMapper;
import com.tmk.ecom.product_service.model.Category;
import com.tmk.ecom.product_service.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.createCategory(categoryRequestDto);
        CategoryResponseDto responseDto = CategoryMapper.toCategoryResponseDto(category);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CategoryResponseDto responseDto = CategoryMapper.toCategoryResponseDtoWithProducts(category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryResponseDto> responseDtos = categories.stream()
                .map(CategoryMapper::toCategoryResponseDto)
                .toList();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        Category updatedCategory = categoryService.updateCategory(id, categoryRequestDto);
        if (updatedCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CategoryResponseDto responseDto = CategoryMapper.toCategoryResponseDto(updatedCategory);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryService.deleteCategory(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
