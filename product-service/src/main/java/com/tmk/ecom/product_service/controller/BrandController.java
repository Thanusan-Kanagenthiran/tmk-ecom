package com.tmk.ecom.product_service.controller;

import com.tmk.ecom.product_service.dto.BrandRequestDto;
import com.tmk.ecom.product_service.dto.BrandResponseDto;
import com.tmk.ecom.product_service.dto.CategoryResponseDto;
import com.tmk.ecom.product_service.mapper.BrandMapper;
import com.tmk.ecom.product_service.model.Brand;
import com.tmk.ecom.product_service.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandResponseDto> createBrand(@RequestBody BrandRequestDto brandRequestDto) {
        Brand brand = brandService.createBrand(brandRequestDto);
        BrandResponseDto responseDto = BrandMapper.toBrandResponseDto(brand);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDto> getBrandById(@PathVariable Long id) {
        Brand brand = brandService.getBrandById(id);
        if (brand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BrandResponseDto responseDto = BrandMapper.toBrandResponseDtoWithProducts(brand);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/brand/{brandId}/categories")
    public ResponseEntity<List<CategoryResponseDto>> getCategoriesByBrand(@PathVariable Long brandId) {
        List<CategoryResponseDto> categories = categoryService.getCategoriesByBrand(brandId);
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<BrandResponseDto>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        List<BrandResponseDto> responseDtos = brands.stream()
                .map(BrandMapper::toBrandResponseDto)
                .toList();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponseDto> updateBrand(@PathVariable Long id, @RequestBody BrandRequestDto brandRequestDto) {
        Brand updatedBrand = brandService.updateBrand(id, brandRequestDto);
        if (updatedBrand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BrandResponseDto responseDto = BrandMapper.toBrandResponseDto(updatedBrand);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        if (brandService.deleteBrand(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
