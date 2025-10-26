package com.example.blogsite.controller;

import com.example.blogsite.domain.dto.CategoryDto;
import com.example.blogsite.domain.dto.CreateCategoryRequest;
import com.example.blogsite.domain.entity.Category;
import com.example.blogsite.mapper.CategoryMapper;
import com.example.blogsite.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtos =  categoryService.getAllCategories()
                    .stream().map(categoryMapper::toDto).toList();

        return ResponseEntity.ok(categoryDtos);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category currCategory = categoryService.createCategory(categoryMapper.toEntity(createCategoryRequest));
        CategoryDto nwCategoryDto = categoryMapper.toDto(currCategory);
        return new ResponseEntity<>(nwCategoryDto, HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
