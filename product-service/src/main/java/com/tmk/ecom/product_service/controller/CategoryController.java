package com.tmk.ecom.product_service.controller;

import com.tmk.ecom.product_service.dto.CategoryRequestDto;
import com.tmk.ecom.product_service.dto.CategoryResponseDto;
import com.tmk.ecom.product_service.dto.ResponseDto;
import com.tmk.ecom.product_service.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService iCategoryService;

    @PostMapping
    public ResponseEntity<ResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto category) {
        iCategoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED, "Category created successfully"));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> categories = iCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryWithProducts(@PathVariable Integer id) {
        CategoryResponseDto categories = iCategoryService.getCategoryWithProducts(id);
        return ResponseEntity.ok(categories);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryRequestDto category) {
        iCategoryService.updateCategory(id, category);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK, "Category updated successfully"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable Integer id) {
        iCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
