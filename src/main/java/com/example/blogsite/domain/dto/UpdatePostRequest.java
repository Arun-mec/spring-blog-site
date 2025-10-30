package com.example.blogsite.domain.dto;

import com.example.blogsite.domain.PostStatus;
import com.example.blogsite.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {
    private String title;
    private String content;
    private UUID category;
    @Builder.Default
    private Set<UUID> tags = new HashSet<>();
    private PostStatus status;
}
