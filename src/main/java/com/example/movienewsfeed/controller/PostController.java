package com.example.movienewsfeed.controller;

import com.example.movienewsfeed.dto.PostAddRequestDto;
import com.example.movienewsfeed.dto.PostResponseDto;
import com.example.movienewsfeed.dto.PostUpdateRequestDto;
import com.example.movienewsfeed.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> addPost(@RequestBody PostAddRequestDto requestDto) {
        PostResponseDto responseDto = postService.addPost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> responseDto = postService.getPosts();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto responseDto = postService.getPost(postId);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto requestDto) {
        PostResponseDto responseDto = postService.updatePost(postId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}