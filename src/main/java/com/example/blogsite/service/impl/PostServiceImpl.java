package com.example.blogsite.service.impl;

import com.example.blogsite.domain.PostStatus;
import com.example.blogsite.domain.dto.PostRequest;
import com.example.blogsite.domain.entity.Category;
import com.example.blogsite.domain.entity.Post;
import com.example.blogsite.domain.entity.Tag;
import com.example.blogsite.domain.entity.User;
import com.example.blogsite.repository.PostRepository;
import com.example.blogsite.service.CategoryService;
import com.example.blogsite.service.PostService;
import com.example.blogsite.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
            posts = postRepository.findByPostStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED, category, tag
            );
        }
        else if (categoryId!=null) {
            category = categoryService.getCategoryById(categoryId);
            posts = postRepository.findByPostStatusAndCategory(
                    PostStatus.PUBLISHED, category
            );
        }
        else if (tagId!=null) {
            tag = tagService.getTagById(tagId);
            posts = postRepository.findByPostStatusAndTagsContaining(
                    PostStatus.PUBLISHED, tag
            );
        }
        else {
            posts = postRepository.findByPostStatus(
                    PostStatus.PUBLISHED
            );
        }

        return posts;
    }

    @Override
    public List<Post> getAllDraftPosts(User user) {
        return postRepository.findByPostStatusAndUser(PostStatus.DRAFT, user);
    }

    @Override
    public Post createPost(PostRequest postRequest, User user) {

        Category category = categoryService.getCategoryById(postRequest.getCategory());
        Set<Tag> tags = tagService.getTagByIds(postRequest.getTags());

        Post newPost = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postStatus(postRequest.getStatus())
                .readingTime(calculatedTime(postRequest.getContent()))
                .user(user)
                .category(category)
                .tags(tags)
                .build();

        return postRepository.save(newPost);
    }

    @Override
    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }

    protected int calculatedTime(String content) {
        if (content.isEmpty())
            return 0;
        int wordLength = content.trim().split("\\s+").length;
        return Math.max(1, wordLength/200);
    }

}
