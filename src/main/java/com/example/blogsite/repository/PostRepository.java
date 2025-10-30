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

    List<Post> findByStatusAndCategoryAndTagsContaining(PostStatus postStatus, Category category, Tag tag);

    List<Post> findByStatusAndCategory(PostStatus postStatus, Category category);

    List<Post> findByStatusAndTagsContaining(PostStatus postStatus, Tag tag);

    List<Post> findByStatus(PostStatus postStatus);

    List<Post> findByStatusAndUser(PostStatus postStatus, User user);
}
