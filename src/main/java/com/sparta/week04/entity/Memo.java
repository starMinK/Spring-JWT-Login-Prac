package com.sparta.week04.entity;

import com.sparta.week04.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Memo(MemoRequestDto memoRequestDto, String userName) {
        this.title = memoRequestDto.getTitle();
        this.contents = memoRequestDto.getContents();
        this.userName = userName;
    }

    public void update(MemoRequestDto memoRequestDto) {
        this.title = memoRequestDto.getTitle();
        this.contents = memoRequestDto.getContents();
    }
}
