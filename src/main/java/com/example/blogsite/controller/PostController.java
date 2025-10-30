package com.example.blogsite.controller;

import com.example.blogsite.domain.dto.*;
import com.example.blogsite.domain.entity.Category;
import com.example.blogsite.domain.entity.Post;
import com.example.blogsite.domain.entity.Tag;
import com.example.blogsite.domain.entity.User;
import com.example.blogsite.mapper.PostMapper;
import com.example.blogsite.repository.UserRepository;
import com.example.blogsite.service.CategoryService;
import com.example.blogsite.service.PostService;
import com.example.blogsite.service.TagService;
import com.example.blogsite.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserService userService;

    // Query params for filtering
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false) UUID category,
            @RequestParam(required = false) UUID tag) {
        List<Post> currPosts = postService.getAllPosts(category, tag);
        List<PostDto> posts = currPosts.stream()
                .map(postMapper::toDto).toList();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{userid}/drafts")
    public ResponseEntity<List<PostDto>> getAllDraftPosts(@RequestParam UUID userid) {
        User loggedInUser = userService.getUserById(userid);
        List<Post> currDraftPosts = postService.getAllDraftPosts(loggedInUser);
        List<PostDto> draftPosts = currDraftPosts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(draftPosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@RequestParam UUID id) {
        Post post = postService.getPostById(id);
        PostDto postDto = postMapper.toDto(post);
        return ResponseEntity.ok(postDto);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostRequestDto postRequestDto,
            @RequestParam UUID userid) {
        User loggedInUser = userService.getUserById(userid);
        PostRequest postRequest = postMapper.toPostRequest(postRequestDto);
        Post createdPost = postService.createPost(postRequest, loggedInUser);
        PostDto response = postMapper.toDto(createdPost);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody UpdatePostReqDto updatePostReqDto, @PathVariable UUID id) {
        UpdatePostRequest updatePostRequest = postMapper.toUpdateRequest(updatePostReqDto);
        Post updatedPost = postService.updatePost(id, updatePostRequest);
        PostDto updatedPostDto = postMapper.toDto(updatedPost);
        return ResponseEntity.ok(updatedPostDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
