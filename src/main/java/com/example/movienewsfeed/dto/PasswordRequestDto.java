package com.example.movienewsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordRequestDto {

    // TODO: 11/21/23 회원 가입과 비밀번호 제약조건 동일하게 설정해주기
    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirmation;
}
