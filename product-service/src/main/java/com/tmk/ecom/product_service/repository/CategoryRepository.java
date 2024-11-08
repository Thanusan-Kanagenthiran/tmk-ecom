package com.tmk.ecom.product_service.repository;

import com.tmk.ecom.product_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Integer> {
    Optional<Object> findByName( String name);
}
