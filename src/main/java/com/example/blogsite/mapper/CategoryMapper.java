package com.example.blogsite.mapper;

import com.example.blogsite.domain.PostStatus;
import com.example.blogsite.domain.dto.CategoryDto;
import com.example.blogsite.domain.dto.CreateCategoryRequest;
import com.example.blogsite.domain.entity.Category;
import com.example.blogsite.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source="posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if (posts==null) return 0;
        return posts.stream()
                .filter(post -> post.getPostStatus().equals(PostStatus.PUBLISHED))
                .count();
    }

    Category toEntity(CreateCategoryRequest categoryRequest);
}
