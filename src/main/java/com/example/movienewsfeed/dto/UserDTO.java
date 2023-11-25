package com.example.movienewsfeed.dto;

import com.example.movienewsfeed.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;

    public UserDTO(User user) {
        this.username = user.getUsername();
    }
}
