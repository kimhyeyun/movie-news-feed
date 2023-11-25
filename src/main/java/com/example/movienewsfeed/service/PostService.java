package com.example.movienewsfeed.service;

import com.example.movienewsfeed.controller.exception.PostNotFoundException;
import com.example.movienewsfeed.dto.PostAddRequestDto;
import com.example.movienewsfeed.dto.PostResponseDto;
import com.example.movienewsfeed.dto.PostUpdateRequestDto;
import com.example.movienewsfeed.entity.Post;
import com.example.movienewsfeed.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;

    public PostResponseDto addPost(PostAddRequestDto requestDto) {
        Post postEntity = new Post(requestDto);
        Post savePost = postJpaRepository.save(postEntity);
        return new PostResponseDto(savePost);
    }

    public PostResponseDto getPost(Long postId) {
        Post post = getPostById(postId);
        return new PostResponseDto(post);
    }

    public List<PostResponseDto> getPosts() {
        return postJpaRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new).toList();
        //https://binux.tistory.com/146
        //toList()와 collect()의 차이
        //toList()는 Collectors.UnmodifiableList 또는 Collectors.UnmodifiableRandomAccessList를 반환
        //collect()는 ArrayList를 반환
        //이후 수정 로직이 존재한다면 toList()로 대체하지 못한다
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Post post = getPostById(postId);
        post.update(requestDto);
        return new PostResponseDto(post);
    }

    public void deletePost(Long postId) {
        Post post = getPostById(postId);
        postJpaRepository.delete(post);
    }

    private Post getPostById(long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));
    }
}
