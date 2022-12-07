package com.sparta.week04.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String password;
}
