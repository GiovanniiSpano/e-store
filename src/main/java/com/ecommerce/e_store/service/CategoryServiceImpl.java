package com.ecommerce.e_store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.e_store.model.Category;
import com.ecommerce.e_store.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public String createCategory(Category category) {
        categoryRepository.save(category);
        return "Categoria aggiunta con successo";
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria non trovata"));
        
        categoryRepository.delete(category);
        return "Categoria con categoryId: " + categoryId + " eliminata con successo";
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria non trovata"));

        savedCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(savedCategory);

        return "Categoria con categoryId: " + categoryId + " aggiornata con successo";
    }
}
