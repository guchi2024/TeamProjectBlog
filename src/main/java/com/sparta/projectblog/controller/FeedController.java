package com.sparta.projectblog.controller;

import com.sparta.projectblog.dto.FeedCreateRequestDto;
import com.sparta.projectblog.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping("")
    public ResponseEntity<Void> feeds (@RequestBody FeedCreateRequestDto requestDto) {
        try {
            feedService.createFeed(requestDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
}

// void : 아무값이 없다 라는 type
// 소문자 대문자 차이 : void 프리머티브 Void : 래퍼클래스
