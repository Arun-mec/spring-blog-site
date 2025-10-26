package com.example.blogsite.service;

import com.example.blogsite.domain.dto.CategoryDto;
import com.example.blogsite.domain.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAllCategories();

    Category createCategory(Category category);

    void deleteCategory(UUID id);
}
