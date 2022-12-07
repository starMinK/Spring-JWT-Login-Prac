package com.sparta.week04.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginRequestDto {

    private String username;
    private String password;
}
