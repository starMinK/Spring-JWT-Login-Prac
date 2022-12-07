package com.sparta.week04.service;

import com.sparta.week04.dto.DeleteResponseDto;
import com.sparta.week04.dto.MemoRequestDto;
import com.sparta.week04.dto.MemoResponseDto;
import com.sparta.week04.entity.Memo;
import com.sparta.week04.entity.User;
import com.sparta.week04.jwt.JwtUtil;
import com.sparta.week04.repository.MemoRepository;
import com.sparta.week04.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jdk.jfr.Frequency;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Memo memo = memoRepository.saveAndFlush(new Memo(memoRequestDto, user.getUsername()));
//            Memo memo = memoRepository.saveAndFlush(new Memo(memoRequestDto, "username1"));

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
                    .id(memos.getId())
                    .title(memos.getTitle())
                    .username(memos.getUserName())
                    .contents(memos.getContents())
                    .createdAt(memos.getCreatedAt())
                    .modifiedAt(memos.getModifiedAt())
                    .build();
                    //.CreatedAt

            memoResponseDtoList.add(memoResponseDto);
        }
        return memoResponseDtoList;
    }

    @Transactional
    public MemoResponseDto readOne(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        return new MemoResponseDto(memo);
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto memoRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Memo memo = memoRepository.findByIdAndUserName(id, user.getUsername())
                    .orElseThrow(
                            () -> new NullPointerException("해당 글은 존재하지 않는 글입니다.")
                    );

            memo.update(memoRequestDto);

            return new MemoResponseDto(memo);
        } else {
            return null;
        }
    }

    @Transactional
    public DeleteResponseDto DeleteOne(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Memo memo = memoRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당하는 아이디가 없습니다")
            );

            if (Objects.equals(memo.getUserName(), user.getUsername())) {
                memoRepository.deleteByIdAndUserName(id, user.getUsername());
                return new DeleteResponseDto("게시글 삭제 성공", 200);
            } else {
                return new DeleteResponseDto("게시글 삭제 실패", 404);
            }
        } else {
            return new DeleteResponseDto("게시글 삭제 실패", 404);
        }
    }

}
