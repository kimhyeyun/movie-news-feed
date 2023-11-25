package com.example.movienewsfeed.dto;

import lombok.Getter;

@Getter
public class PostAddRequestDto {
    private String title;
    private String content;
    private String thumbnail;
}
