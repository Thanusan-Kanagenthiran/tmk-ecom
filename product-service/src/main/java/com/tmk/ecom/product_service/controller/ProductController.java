package com.tmk.ecom.product_service.controller;

import com.tmk.ecom.product_service.dto.ProductRequestDto;
import com.tmk.ecom.product_service.dto.ProductResponseDto;
import com.tmk.ecom.product_service.mapper.ProductMapper;
import com.tmk.ecom.product_service.model.Product;
import com.tmk.ecom.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.createProduct(productRequestDto);
        ProductResponseDto responseDto = ProductMapper.toProductResponseDto(product);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductResponseDto responseDto = ProductMapper.toProductResponseDtoWithDetails(product);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> responseDtos = products.stream()
                .map(ProductMapper::toProductResponseDto)
                .toList();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        Product updatedProduct = productService.updateProduct(id, productRequestDto);
        if (updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductResponseDto responseDto = ProductMapper.toProductResponseDto(updatedProduct);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
