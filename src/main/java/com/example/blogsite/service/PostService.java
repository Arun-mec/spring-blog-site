package com.example.blogsite.service;

import com.example.blogsite.domain.dto.PostRequest;
import com.example.blogsite.domain.dto.UpdatePostRequest;
import com.example.blogsite.domain.entity.Category;
import com.example.blogsite.domain.entity.Post;
import com.example.blogsite.domain.entity.Tag;
import com.example.blogsite.domain.entity.User;
import org.mapstruct.control.MappingControl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PostService {

    List<Post> getAllPosts(UUID categoryId, UUID tagId);

    List<Post> getAllDraftPosts(User user);

    Post createPost(PostRequest postRequest, User user);

    void deletePost(UUID id);

    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);

    Post getPostById(UUID id);
}
