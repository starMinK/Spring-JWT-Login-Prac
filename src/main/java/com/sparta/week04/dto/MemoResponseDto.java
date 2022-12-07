package com.sparta.week04.dto;

import com.sparta.week04.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class MemoResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.username = memo.getUserName();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
    }
}
