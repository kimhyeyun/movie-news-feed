package com.example.movienewsfeed.service;

import com.example.movienewsfeed.controller.exception.PostNotFoundException;
import com.example.movienewsfeed.dto.PostRequestDto;
import com.example.movienewsfeed.dto.PostResponseDto;
import com.example.movienewsfeed.entity.Post;
import com.example.movienewsfeed.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;

    public PostResponseDto getPost(Long postId) {
        Post post = getPostEntity(postId);
        return new PostResponseDto(post);
    }

    public PostResponseDto addPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        Post savePost = postJpaRepository.save(post);
        return new PostResponseDto(savePost);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto) {
        Post post = getPostEntity(postId);
        post.update(requestDto);
        return new PostResponseDto(post);
    }

    public void deletePost(Long postId) {
        Post post = getPostEntity(postId);
        postJpaRepository.delete(post);
    }

    private Post getPostEntity(long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));
    }
}
