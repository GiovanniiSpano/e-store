package com.ecommerce.e_store.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.e_store.exception.APIException;
import com.ecommerce.e_store.exception.ResourceNotFoundException;
import com.ecommerce.e_store.model.Category;
import com.ecommerce.e_store.payload.CategoryDTO;
import com.ecommerce.e_store.payload.CategoryResponse;
import com.ecommerce.e_store.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize) {

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = this.categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();

        if (categories.isEmpty()) {
            throw new APIException("Nessuna categoria aggiunta finora");
        }

        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(category -> this.modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setContent(categoryDTOs);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Optional<Category> cat = this.categoryRepository.findByCategoryName(categoryDTO.getCategoryName());

        if (cat.isPresent()) {
            throw new APIException("Categoria con categoryName: " + categoryDTO.getCategoryName() + " già esistente");
        }

        Category category = this.modelMapper.map(categoryDTO, Category.class);

        CategoryDTO savedCategoryDTO = this.modelMapper.map(this.categoryRepository.save(category), CategoryDTO.class);

        return savedCategoryDTO;
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("'Categoria'", "categoryId", categoryId));

        CategoryDTO deletedCategoryDTO = this.modelMapper.map(category, CategoryDTO.class);

        categoryRepository.delete(category);

        return deletedCategoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category cat = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("'Categoria'", "categoryId", categoryId));

        cat.setCategoryName(categoryDTO.getCategoryName());

        CategoryDTO updatedCategoryDTO = this.modelMapper.map(this.categoryRepository.save(cat), CategoryDTO.class);

        return updatedCategoryDTO;
    }
}
