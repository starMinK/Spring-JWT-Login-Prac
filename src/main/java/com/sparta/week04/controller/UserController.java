package com.sparta.week04.controller;

import com.sparta.week04.dto.LoginRequestDto;
import com.sparta.week04.dto.LoginResponseDto;
import com.sparta.week04.dto.SignupRequestDto;
import com.sparta.week04.dto.SignupResponseDto;
import com.sparta.week04.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    //1 회원가입 api
    //2 username, password를 client에서 전달받기
    //3 username은 최소 4자 이상, 10자 이하이며 알파벳 소문자, 숫자로 구성되어야 한다.
    //4 password는 최소 8사 이상, 15자 이하, 알파벳 대소문자, 숫자로 구성되어야 한다.
    //5 db에 중복된 username이 없다면 회원을 저장하고 client로 성공했다는 메세지, 상태코드 반환

    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        LoginRequestDto asd123 = LoginRequestDto.builder().username("asd123").password("123").build();
        return userService.login(loginRequestDto, response);
    }
}
