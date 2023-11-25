package com.example.movienewsfeed.service;

import com.example.movienewsfeed.dto.CommonResponseDto;
import com.example.movienewsfeed.dto.PasswordRequestDto;
import com.example.movienewsfeed.dto.ProfileRequestDto;
import com.example.movienewsfeed.dto.UserRequestDto;
import com.example.movienewsfeed.entity.User;
import com.example.movienewsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void signup(UserRequestDto userRequestDto){
        String username = userRequestDto.getUsername();
        String password = passwordEncoder.encode(userRequestDto.getPassword());

        if(userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 유저 입니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);
    }

    public void login(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("등록된 유저가 없습니다."));

        return user;
    }

    @Transactional
    public ResponseEntity<CommonResponseDto> modifyProfile(ProfileRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("등록된 유저가 없습니다."));

        user.updateIntroduction(requestDto);
        return ResponseEntity.ok().body(new CommonResponseDto("프로필 수정 성공", HttpStatus.OK.value()));
    }

    @Transactional
    public ResponseEntity<CommonResponseDto> modifyPassword(PasswordRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("등록된 유저가 없습니다."));

        user.updatePassword(passwordEncoder.encode(requestDto.getPassword()));
        return ResponseEntity.ok().body(new CommonResponseDto("비밀번호 수정 성공", HttpStatus.OK.value()));
    }
}
