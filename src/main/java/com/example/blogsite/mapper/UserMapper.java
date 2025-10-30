package com.example.blogsite.mapper;

import com.example.blogsite.domain.dto.UserDto;
import com.example.blogsite.domain.dto.UserRequest;
import com.example.blogsite.domain.dto.UserRequestDto;
import com.example.blogsite.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "role", source = "role")
    UserRequest toUserRequest(UserRequestDto userRequestDto);

    UserDto toDto(User user);
}
