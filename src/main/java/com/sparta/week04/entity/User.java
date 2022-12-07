package com.sparta.week04.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp ="^[a-z0-9].{4,10}$", message = "4~10자리의 영소문자, 숫자만 입력 가능합니다.")
    private String username;

    @Column(nullable = false)
    @Pattern(regexp ="^[a-zA-Z0-9].{8,15}$", message = "8~15자리의 영소문자, 영대문자, 숫자만 입력 가능합니다.")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
