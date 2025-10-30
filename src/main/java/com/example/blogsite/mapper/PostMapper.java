package com.example.blogsite.mapper;

import com.example.blogsite.domain.dto.PostDto;
import com.example.blogsite.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target="author", source = "user")
    @Mapping(target="category", source = "category")
    @Mapping(target="tags", source = "tags")
    PostDto toDto(Post post);

}
