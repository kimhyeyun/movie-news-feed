package com.example.movienewsfeed.dto;

import com.example.movienewsfeed.entity.Post;
import com.example.movienewsfeed.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private String title;
    private User user;
    private String content;
    private String thumbnail;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.user = post.getUser();
        this.content = post.getContent();
        this.thumbnail = post.getThumbnail();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
