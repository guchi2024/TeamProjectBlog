package com.sparta.projectblog.service;

import com.sparta.projectblog.dto.FeedCreateRequestDto;
import com.sparta.projectblog.entity.Feed;
import com.sparta.projectblog.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;

    public void createFeed(FeedCreateRequestDto requestDto) {
        Feed feed = Feed.builder()
                .userId(1L)
                .content(requestDto.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        feedRepository.save(feed);
    }
}
