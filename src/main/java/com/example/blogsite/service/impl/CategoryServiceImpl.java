package com.example.blogsite.service.impl;

import com.example.blogsite.domain.dto.CategoryDto;
import com.example.blogsite.domain.entity.Category;
import com.example.blogsite.repository.CategoryRepository;
import com.example.blogsite.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName()))
            throw new IllegalArgumentException("Category already exists with name: "+category.getName());

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> currCategory = categoryRepository.findById(id);
        if (currCategory.isPresent()){
            if (!currCategory.get().getPosts().isEmpty()) {
                throw new IllegalStateException("Category has posts associated with it!");
            }
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public Category getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("No category found with id: "+categoryId));
    }

}
