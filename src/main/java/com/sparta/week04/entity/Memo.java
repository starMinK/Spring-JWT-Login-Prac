package com.sparta.week04.entity;

import com.sparta.week04.dto.MemoRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Memo extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Memo(MemoRequestDto memoRequestDto, Long id, String userName) {
        this.id = id;
        this.title = memoRequestDto.getTitle();
        this.contents = memoRequestDto.getContents();
        this.userName = userName;
    }
}
