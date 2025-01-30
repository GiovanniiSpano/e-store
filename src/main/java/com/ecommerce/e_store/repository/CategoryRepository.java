package com.ecommerce.e_store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.e_store.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Optional<Category> findByCategoryName(String categoryName);
}
