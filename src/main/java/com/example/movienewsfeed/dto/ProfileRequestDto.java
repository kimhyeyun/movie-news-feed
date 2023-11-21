package com.example.movienewsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProfileRequestDto {

    @NotBlank
    @Pattern(regexp = "^[가-힣]{2,5}", message = "한글만 사용하여 2-5자로 작성해주세요.")
    private String name;

    @NotBlank
    private String introduction;
}
