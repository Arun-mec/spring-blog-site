package com.example.blogsite.service.impl;

import com.example.blogsite.domain.entity.Tag;
import com.example.blogsite.repository.TagRepository;
import com.example.blogsite.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAllWithPostCount();
    }

    @Override
    public List<Tag> createTags(Set<String> tags) {
        // List of tags
        List<Tag> existingTagsInDb = tagRepository.findByNameIn(tags);
        // Converting to list of string
        Set<String> existingTags = existingTagsInDb.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
        // Comparing with existing and adding if new
        List<Tag> newTags = tags.stream()
                .filter(tagName -> !existingTags.contains(tagName))
                .map(tag -> Tag.builder()
                        .name(tag)
                        .posts(new HashSet<>())
                        .build()
                ).toList();

        List<Tag> savedTags = new ArrayList<>();
        if (!newTags.isEmpty())
            savedTags = tagRepository.saveAll(newTags);

        savedTags.addAll(existingTagsInDb);

        return savedTags;
    }

    @Override
    public void deleteTag(UUID id) {
        Optional<Tag> currentTag = tagRepository.findById(id);
        if (currentTag.isPresent())
            tagRepository.deleteById(id);
        else
            throw new IllegalStateException("Unable to delete the post!");
    }

    @Override
    public Tag getTagById(UUID tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new EntityNotFoundException("No tag found with id: "+tagId));
    }

    @Override
    public Set<Tag> getTagByIds(Set<UUID> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) {
            throw new IllegalArgumentException("Tag IDs must not be null or empty");
        }

        List<Tag> foundTags = tagRepository.findTagsByIds(tagIds);

        if (foundTags.isEmpty()) {
            throw new EntityNotFoundException("No tags found for the given IDs");
        }

        if (foundTags.size() != tagIds.size()) {
            Set<UUID> foundIds = foundTags.stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());
            Set<UUID> missing = tagIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .collect(Collectors.toSet());

            throw new EntityNotFoundException("Tags not found for IDs: " + missing);
        }

        return new HashSet<>(foundTags);
    }


}
