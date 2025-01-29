package com.ecommerce.e_store.service;

import java.util.List;

import com.ecommerce.e_store.model.Category;

public interface CategoryService {
    List<Category> getAllCategories();
    String createCategory(Category category);
    String deleteCategory(Long categoryId);
    String updateCategory(Category category, Long categoryId);
}
