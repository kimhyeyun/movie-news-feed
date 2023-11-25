package com.example.movienewsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProfileRequestDto {

    @NotBlank
    private String introduction;
}
