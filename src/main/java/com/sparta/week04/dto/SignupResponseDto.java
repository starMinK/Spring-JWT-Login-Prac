package com.sparta.week04.dto;

import com.sparta.week04.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupResponseDto {
    private Long id;
    private String username;
    private String password;

    public SignupResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
