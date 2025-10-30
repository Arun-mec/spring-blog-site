package com.example.blogsite.repository;

import com.example.blogsite.domain.PostStatus;
import com.example.blogsite.domain.entity.Category;
import com.example.blogsite.domain.entity.Post;
import com.example.blogsite.domain.entity.Tag;
import com.example.blogsite.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findByPostStatusAndCategoryAndTagsContaining(PostStatus postStatus, Category category, Tag tag);

    List<Post> findByPostStatusAndCategory(PostStatus postStatus, Category category);

    List<Post> findByPostStatusAndTagsContaining(PostStatus postStatus, Tag tag);

    List<Post> findByPostStatus(PostStatus postStatus);

    List<Post> findByPostStatusAndUser(PostStatus postStatus, User user);
}
