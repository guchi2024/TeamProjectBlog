package com.sparta.projectblog.controller;

import com.sparta.projectblog.dto.FeedCreateRequestDto;
import com.sparta.projectblog.dto.FeedUpdateRequestDto;
import com.sparta.projectblog.entity.Feed;
import com.sparta.projectblog.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping("")
    public ResponseEntity<Void> feeds(@RequestBody FeedCreateRequestDto requestDto) {
        try {
            feedService.createFeed(requestDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public Feed feeds(@PathVariable Long id) {
        return feedService.readFeed(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateFeed(@PathVariable Long id, @RequestBody FeedUpdateRequestDto requestDto) {
        try {
            feedService.updateFeed(id, requestDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Long id) {
        try {
            feedService.deleteFeed(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


// void : 아무값이 없다 라는 type
// 소문자 대문자 차이 : void 프리머티브 Void : 래퍼클래스
