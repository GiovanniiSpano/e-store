package com.ecommerce.e_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.e_store.exception.APIException;
import com.ecommerce.e_store.exception.ResourceNotFoundException;
import com.ecommerce.e_store.model.Category;
import com.ecommerce.e_store.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();

        if (categories.isEmpty()) throw new APIException("Nessuna categoria aggiunta finora");

        return categories;
    }

    @Override
    public String createCategory(Category category) {
        Optional<Category> cat = this.categoryRepository.findByCategoryName(category.getCategoryName());

        if (cat.isPresent()) throw new APIException("Categoria con categoryName: " + category.getCategoryName() + " già esistente");

        this.categoryRepository.save(category);
        return "Categoria aggiunta con successo";
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("'Categoria'", "categoryId", categoryId));
        
        categoryRepository.delete(category);
        return "Categoria con categoryId: " + categoryId + " eliminata con successo";
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {
        Category savedCategory = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("'Categoria'", "categoryId", categoryId));

        savedCategory.setCategoryName(category.getCategoryName());
        this.categoryRepository.save(savedCategory);

        return "Categoria con categoryId: " + categoryId + " aggiornata con successo";
    }
}
