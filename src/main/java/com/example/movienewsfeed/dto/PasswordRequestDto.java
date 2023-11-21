package com.example.movienewsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordRequestDto {

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirmation;
}
