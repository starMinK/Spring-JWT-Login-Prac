package com.sparta.week04.controller;

import com.sparta.week04.dto.MemoRequestDto;
import com.sparta.week04.dto.MemoResponseDto;
import com.sparta.week04.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/post")
    public MemoResponseDto create(@RequestBody MemoRequestDto memoRequestDto, HttpServletRequest request) {
        return memoService.create(memoRequestDto, request);
    }

    @GetMapping("/posts")
    public List<MemoResponseDto> readAll() {
        return memoService.readAll();
    }

    @GetMapping("/post/{id}")
    public MemoResponseDto readOne(@PathVariable Long id) {
        return memoService.readOne();
    }

    @PutMapping("/post/{id}")
    public MemoResponseDto update(@RequestBody MemoRequestDto memoRequestDto, @PathVariable Long id) {
        return memoService.update();
    }

    @DeleteMapping("/post/{id}")
    public MemoResponseDto delete(@PathVariable Long id) {
        return memoService.DeleteOne();
    }
}
