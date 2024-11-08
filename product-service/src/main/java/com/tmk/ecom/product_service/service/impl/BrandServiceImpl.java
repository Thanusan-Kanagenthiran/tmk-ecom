package com.tmk.ecom.product_service.service.impl;

import com.tmk.ecom.product_service.dto.BrandRequestDto;
import com.tmk.ecom.product_service.dto.BrandResponseDto;
import com.tmk.ecom.product_service.exception.ResourceAlreadyExistException;
import com.tmk.ecom.product_service.exception.ResourceNotFoundException;
import com.tmk.ecom.product_service.mapper.BrandMapper;
import com.tmk.ecom.product_service.model.Brand;
import com.tmk.ecom.product_service.repository.BrandRepository;
import com.tmk.ecom.product_service.service.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements IBrandService {

    private final BrandRepository categoryRepository;

    @Override
    public void createBrand(BrandRequestDto categoryDto) {
        categoryRepository.findByName(categoryDto.getName())
                .ifPresent(existingBrand -> {
                    throw new ResourceAlreadyExistException("Brand", "name");
                });
        categoryRepository.save(BrandMapper.toBrand(categoryDto));
    }

    @Override
    public List<BrandResponseDto> getAllBrands() {
        return categoryRepository.findAll()
                .stream()
                .map(BrandMapper::toBrandResponseDto)
                .toList();
    }

    @Override
    public BrandResponseDto getBrandWithProducts(Integer id) {
        Brand category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id.toString()));
        return BrandMapper.toBrandResponseDtoWithProducts(category);
    }

    @Override
    public void updateBrand(Integer id, BrandRequestDto categoryDto) {
        Brand existingBrand = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id.toString()));

        if (!existingBrand.getName().equals(categoryDto.getName())) {
            categoryRepository.findByName(categoryDto.getName())
                    .ifPresent(existing -> {
                        throw new ResourceAlreadyExistException("Brand", "name");
                    });
        }
        existingBrand.setName(categoryDto.getName());
        categoryRepository.save(existingBrand);
    }

    @Override
    public void deleteBrand(Integer id) {
        Brand category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id.toString()));
        categoryRepository.delete(category);
    }

}
