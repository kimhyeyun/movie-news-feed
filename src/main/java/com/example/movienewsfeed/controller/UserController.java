package com.example.movienewsfeed.controller;

import com.example.movienewsfeed.dto.CommonResponseDto;
import com.example.movienewsfeed.dto.PasswordRequestDto;
import com.example.movienewsfeed.dto.ProfileRequestDto;
import com.example.movienewsfeed.entity.User;
import com.example.movienewsfeed.jwt.JwtUtil;
import com.example.movienewsfeed.dto.UserRequestDto;
import com.example.movienewsfeed.service.UserService;
import com.example.movienewsfeed.user.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        try {
            userService.signup(userRequestDto);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("중복된 이름 입니다.", HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response) {
        try {
            userService.login(userRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(userRequestDto.getUsername()));

        return ResponseEntity.ok().body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));
    }

    @GetMapping("/{userId}")
    public ModelAndView profile(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userService.getUser(userId);

        ModelAndView modelAndView = new ModelAndView("/profile");
        modelAndView.addObject("user", user);
        modelAndView.addObject("isOwner", Objects.equals(user.getId(), userDetails.getUser().getId()));

        return modelAndView;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<CommonResponseDto> modifyProfile(@PathVariable Long userId,
                                                           @RequestBody ProfileRequestDto requestDto,
                                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (!Objects.equals(userId, userDetails.getUser().getId())) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("본인의 프로필만 수정 가능합니다", HttpStatus.BAD_REQUEST.value()));
        }

        return userService.modifyProfile(requestDto, userId);
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<CommonResponseDto> modifyPassword(@PathVariable Long userId,
                                                            @RequestBody PasswordRequestDto requestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (!Objects.equals(userId, userDetails.getUser().getId())) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("본인의 비밀번호만 수정 가능합니다", HttpStatus.BAD_REQUEST.value()));
        }

        if (!requestDto.getPasswordConfirmation().equals(requestDto.getPassword())) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("변경할 비밀번호와 확인 비밀번호를 동일하게 입력해주세요.", HttpStatus.BAD_REQUEST.value()));
        }

        return userService.modifyPassword(requestDto, userId);
    }
}
