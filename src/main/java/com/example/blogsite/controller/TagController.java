package com.example.blogsite.controller;

import com.example.blogsite.domain.dto.TagRequest;
import com.example.blogsite.domain.dto.TagResponse;
import com.example.blogsite.domain.entity.Tag;
import com.example.blogsite.mapper.TagMapper;
import com.example.blogsite.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        if (tags.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // Convertung to Tagrespose
        List<TagResponse> tagResponse = tags.stream()
                .map(tagMapper::toTagResponse)
                .toList();
        return ResponseEntity.ok(tagResponse);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody TagRequest tagRequest) {
        List<Tag> savedTags = tagService.createTags(tagRequest.getNames());

        List<TagResponse> savedTagResponses = savedTags.stream()
                .map(tagMapper::toTagResponse).toList();

        return new ResponseEntity<>(
                savedTagResponses,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
