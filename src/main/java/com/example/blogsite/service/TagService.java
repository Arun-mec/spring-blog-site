package com.example.blogsite.service;

import com.example.blogsite.domain.entity.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {

    List<Tag> getAllTags();

    List<Tag> createTags(Set<String> tags);

    void deleteTag(UUID id);

    Tag getTagById(UUID tagId);

    Set<Tag> getTagByIds(Set<UUID> tags);
}
