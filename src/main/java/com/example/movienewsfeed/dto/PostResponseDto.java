package com.example.movienewsfeed.dto;

import com.example.movienewsfeed.entity.Post;
import com.example.movienewsfeed.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class PostResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String thumbnail;
    private final User user;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getContent();
        this.content = post.getContent();
        this.thumbnail = post.getThumbnail();
        this.user = post.getUser();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
