package com.example.blogsite.domain.dto;

import com.example.blogsite.domain.PostStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {
    @Nullable
    private String title;
    @Nullable
    private String content;
    @Nullable
    private UUID category;
    @Builder.Default
    @Nullable
    private Set<UUID> tags = new HashSet<>();
    @Nullable
    private PostStatus status;
}
