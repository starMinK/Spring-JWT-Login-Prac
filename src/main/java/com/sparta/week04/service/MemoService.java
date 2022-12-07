package com.sparta.week04.service;

import com.sparta.week04.dto.MemoRequestDto;
import com.sparta.week04.dto.MemoResponseDto;
import com.sparta.week04.entity.Memo;
import com.sparta.week04.entity.User;
import com.sparta.week04.jwt.JwtUtil;
import com.sparta.week04.repository.MemoRepository;
import com.sparta.week04.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional

    public MemoResponseDto create(MemoRequestDto memoRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalStateException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalStateException("사용자가 존재하지 않습니다.")
            );

            Memo memo = memoRepository.saveAndFlush(new Memo(memoRequestDto, user.getId(), user.getUsername()));

            return new MemoResponseDto(memo);
        } else {
            return null;
        }
    }

    @Transactional
    public List<MemoResponseDto> readAll() {
        List<Memo> memo = memoRepository.findAll();
        List<MemoResponseDto> memoResponseDtoList = new ArrayList<>();

        for (Memo memos : memo) {
            MemoResponseDto memoResponseDto = MemoResponseDto.builder()
                    .title(memos.getTitle())
                    .username(memos.getUserName())
                    .contents(memos.getContents())
                    .build();
                    //.CreatedAt

            memoResponseDtoList.add(memoResponseDto);
        }
        return memoResponseDtoList;
    }

    @Transactional
    public MemoResponseDto readOne() {
        return null;
    }

    @Transactional
    public MemoResponseDto update() {
        return null;
    }

    @Transactional
    public MemoResponseDto DeleteOne() {
        return null;
    }
}
