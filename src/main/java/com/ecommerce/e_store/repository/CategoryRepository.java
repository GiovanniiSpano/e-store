package com.ecommerce.e_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.e_store.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
