package com.tmk.ecom.product_service.repository;

import com.tmk.ecom.product_service.model.Brand;
import com.tmk.ecom.product_service.model.Category;
import com.tmk.ecom.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByNameAndCategoryAndBrand(String name, Category category, Brand brand);
}
