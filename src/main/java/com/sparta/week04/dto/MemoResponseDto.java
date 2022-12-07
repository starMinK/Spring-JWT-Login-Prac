package com.sparta.week04.dto;

import com.sparta.week04.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemoResponseDto {
    private String title;
    private String contents;
    private String username;

    public MemoResponseDto(Memo memo) {
        this.title = memo.getTitle();
    }
}
