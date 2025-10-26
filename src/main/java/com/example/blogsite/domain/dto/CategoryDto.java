package com.example.blogsite.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CategoryDto {
    private UUID id;
    private String name;
    private int postCount;
}
