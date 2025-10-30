package com.example.blogsite.service.impl;

import com.example.blogsite.domain.PostStatus;
import com.example.blogsite.domain.dto.PostRequest;
import com.example.blogsite.domain.dto.UpdatePostRequest;
import com.example.blogsite.domain.entity.Category;
import com.example.blogsite.domain.entity.Post;
import com.example.blogsite.domain.entity.Tag;
import com.example.blogsite.domain.entity.User;
import com.example.blogsite.repository.PostRepository;
import com.example.blogsite.service.CategoryService;
import com.example.blogsite.service.PostService;
import com.example.blogsite.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    @Override
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        Category category = null;
        Tag tag = null;
        List<Post> posts = new ArrayList<>();
        if (categoryId!=null && tagId!=null) {
            category = categoryService.getCategoryById(categoryId);
            tag = tagService.getTagById(tagId);
            posts = postRepository.findByStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED, category, tag
            );
        }
        else if (categoryId!=null) {
            category = categoryService.getCategoryById(categoryId);
            posts = postRepository.findByStatusAndCategory(
                    PostStatus.PUBLISHED, category
            );
        }
        else if (tagId!=null) {
            tag = tagService.getTagById(tagId);
            posts = postRepository.findByStatusAndTagsContaining(
                    PostStatus.PUBLISHED, tag
            );
        }
        else {
            posts = postRepository.findByStatus(
                    PostStatus.PUBLISHED
            );
        }

        return posts;
    }

    @Override
    public List<Post> getAllDraftPosts(User user) {
        return postRepository.findByStatusAndUser(PostStatus.DRAFT, user);
    }

    @Override
    public Post createPost(PostRequest postRequest, User user) {

        Category category = categoryService.getCategoryById(postRequest.getCategory());
        Set<Tag> tags = tagService.getTagByIds(postRequest.getTags());

        Post newPost = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .status(postRequest.getStatus())
                .readingTime(calculatedTime(postRequest.getContent()))
                .user(user)
                .category(category)
                .tags(tags)
                .build();

        return postRepository.save(newPost);
    }

    @Override
    public void deletePost(UUID id) {
        Post post = postRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No post found with id: "+id));
        postRepository.deleteById(id);
    }

    @Override
    public Post updatePost(UUID id, UpdatePostRequest updatePostRequest) {
        Post currPost = postRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No post found with id: "+id));

        currPost.setTitle(updatePostRequest.getTitle());
        currPost.setContent(updatePostRequest.getContent());
        currPost.setStatus(updatePostRequest.getStatus());
        currPost.setReadingTime(calculatedTime(updatePostRequest.getContent()));

        if (!currPost.getCategory().getId().equals(updatePostRequest.getCategory())) {
            Category category = categoryService.getCategoryById(updatePostRequest.getCategory());
            currPost.setCategory(category);
        }

        Set<UUID> currTagIds = currPost.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
        Set<UUID> newTagIds =  updatePostRequest.getTags().stream()
                .filter(tagId -> !currTagIds.contains(tagId)).collect(Collectors.toSet());
        if (!currTagIds.equals(newTagIds)) {
            Set<Tag> newTags = tagService.getTagByIds(newTagIds);
            currPost.getTags().addAll(newTags);
        }

        return postRepository.save(currPost);
    }

    @Override
    public Post getPostById(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No post found with given id: "+id));
    }

    protected int calculatedTime(String content) {
        if (content.isEmpty())
            return 0;
        int wordLength = content.trim().split("\\s+").length;
        return Math.max(1, wordLength/200);
    }

}
