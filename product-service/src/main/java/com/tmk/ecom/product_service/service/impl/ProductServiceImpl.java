package com.tmk.ecom.product_service.service.impl;

import com.tmk.ecom.product_service.dto.ProductRequestDto;
import com.tmk.ecom.product_service.dto.ProductResponseDto;
import com.tmk.ecom.product_service.exception.InvalidUpdateException;
import com.tmk.ecom.product_service.exception.ResourceAlreadyExistException;
import com.tmk.ecom.product_service.exception.ResourceNotFoundException;
import com.tmk.ecom.product_service.mapper.ProductMapper;
import com.tmk.ecom.product_service.model.Brand;
import com.tmk.ecom.product_service.model.Category;
import com.tmk.ecom.product_service.model.Product;
import com.tmk.ecom.product_service.repository.BrandRepository;
import com.tmk.ecom.product_service.repository.CategoryRepository;
import com.tmk.ecom.product_service.repository.ProductRepository;
import com.tmk.ecom.product_service.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;


    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productRequestDto.getCategoryId().toString()));

        Brand brand = brandRepository.findById(productRequestDto.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", productRequestDto.getBrandId().toString()));

        productRepository.findByNameAndCategoryAndBrand(productRequestDto.getName(), category, brand)
                .ifPresent(existingProduct -> {
                    throw new ResourceAlreadyExistException("Product with this name already exists in the given category of given brand.");
                });

        productRepository.save(ProductMapper.toProduct(productRequestDto, category, brand));
    }


    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toProductResponseDto)
                .toList();
    }

    @Override
    public ProductResponseDto getProductWithDetails(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id.toString()));
        return ProductMapper.toProductResponseDto(product);
    }

    @Override
    public void updateProduct(Integer id, ProductRequestDto product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id.toString()));

        if (!existingProduct.getCategory().getId().equals(product.getCategoryId())) {
            throw new InvalidUpdateException("Category cannot be changed after the product is created.");
        }

        if (!existingProduct.getBrand().getId().equals(product.getBrandId())) {
            throw new InvalidUpdateException("Brand cannot be changed after the product is created.");
        }

         if (!existingProduct.getName().equals(product.getName())) {
            productRepository.findByNameAndCategoryAndBrand(product.getName(), existingProduct.getCategory(), existingProduct.getBrand())
                    .ifPresent(existing -> {
                        throw new ResourceAlreadyExistException("Product with this name already exists in the given category and brand.");
                    });
        }

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setAvailableQuantity(product.getAvailableQuantity());
        existingProduct.setPrice(product.getPrice());

        productRepository.save(existingProduct);
    }


    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id.toString()));
        productRepository.delete(product);
    }
}
