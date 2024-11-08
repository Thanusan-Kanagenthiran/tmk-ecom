package com.tmk.ecom.product_service.controller;

import com.tmk.ecom.product_service.dto.ProductRequestDto;
import com.tmk.ecom.product_service.dto.ProductResponseDto;
import com.tmk.ecom.product_service.dto.ResponseDto;
import com.tmk.ecom.product_service.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @PostMapping
    public ResponseEntity<ResponseDto> createProduct(@Valid @RequestBody ProductRequestDto brand) {
        iProductService.createProduct(brand);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED, "Product created successfully"));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> brands = iProductService.getAllProducts();
        return ResponseEntity.ok(brands);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Integer id) {
        ProductResponseDto brand = iProductService.getProductWithDetails(id);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductRequestDto brand) {
        iProductService.updateProduct(id, brand);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK, "Product updated successfully"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable Integer id) {
        iProductService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
