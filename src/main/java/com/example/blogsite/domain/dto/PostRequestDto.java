package com.example.blogsite.domain.dto;

import com.example.blogsite.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class PostRequestDto {
    @NotBlank(message = "Post title should not be empty!")
    @Size(min = 2, max = 20, message = "Title length should be between {min} and {max}")
    private String title;

    @NotBlank(message = "Post content should not be empty!")
    @Size(min = 10, max = 2000, message = "Content length should be between {min} and {max}")
    private String content;

    @NotNull(message = "Category ID must not be null!")
    private UUID category;

    @Builder.Default
    @NotEmpty(message = "At least one tag must be provided!")
    private Set<UUID> tags = new HashSet<>();

    @NotNull(message = "Post status must not be null!")
    private PostStatus status;
}
