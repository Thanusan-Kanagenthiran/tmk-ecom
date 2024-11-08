package com.tmk.ecom.product_service.controller;

import com.tmk.ecom.product_service.dto.BrandRequestDto;
import com.tmk.ecom.product_service.dto.BrandResponseDto;
import com.tmk.ecom.product_service.dto.ResponseDto;
import com.tmk.ecom.product_service.service.IBrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final IBrandService iBrandService;

    @PostMapping
    public ResponseEntity<ResponseDto> createBrand(@Valid @RequestBody BrandRequestDto brand) {
        iBrandService.createBrand(brand);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED, "Brand created successfully"));
    }

    @GetMapping
    public ResponseEntity<List<BrandResponseDto>> getAllBrands() {
        List<BrandResponseDto> brands = iBrandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }


    @GetMapping("{id}")
    public ResponseEntity<BrandResponseDto> getBrandById(@PathVariable Integer id) {
        BrandResponseDto brand = iBrandService.getBrandWithProducts(id);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> updateBrand(@PathVariable Integer id, @Valid @RequestBody BrandRequestDto brand) {
        iBrandService.updateBrand(id, brand);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK, "Brand updated successfully"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteBrand(@PathVariable Integer id) {
        iBrandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

}
