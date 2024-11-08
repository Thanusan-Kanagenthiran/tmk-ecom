package com.tmk.ecom.product_service.repository;

import com.tmk.ecom.product_service.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Optional<Object> findByName(String name);
}
