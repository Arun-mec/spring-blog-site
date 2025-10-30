package com.example.blogsite.domain.dto;

import com.example.blogsite.domain.entity.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagRequest {
    @NotEmpty(message="Atleast one tag name should be added")
    @Size(max = 10, message = "Maximum {max} tags can allowed")
    private Set<
            @Size(min = 3, max=10, message = "Tag name must be {min} and {max} characters")
            @Pattern(regexp = "^[\\w\\s-]+$", message = "Tag name should only contain characters, numbers, spaces and hyphens!")
            String> names;
}
