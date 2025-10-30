package com.example.blogsite.domain.dto;

import com.example.blogsite.domain.PostStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
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
public class UpdatePostReqDto {

    @Size(max = 30, message = "Title length should {max} characters!")
    private String title;

    @Size(max = 2000, message = "Content length should be {max} characters!")
    private String content;

    private UUID category;

    @Builder.Default
    private Set<UUID> tags = new HashSet<>();

    private PostStatus status;
}
