package com.sparta.projectblog.service;

import com.sparta.projectblog.dto.FeedCreateRequestDto;
import com.sparta.projectblog.dto.FeedUpdateRequestDto;
import com.sparta.projectblog.entity.Feed;
import com.sparta.projectblog.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;

    // Create
    public void createFeed(FeedCreateRequestDto requestDto) {
        Feed feed = Feed.builder()
                .userId(1L)
                .content(requestDto.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        feedRepository.save(feed);
    }

    public Feed readFeed(Long id) {
        return feedRepository.findById(id).orElseThrow(() -> new RuntimeException("Feed Not found"));
    }

    //Update
    public void updateFeed(Long id, FeedUpdateRequestDto requestDto) {
        Feed feed = feedRepository.findById(id).orElseThrow(() -> new RuntimeException("Feed not found"));
        feed.updatedFeed(requestDto.getContent(), requestDto.getUpdatedAt());
        feedRepository.save(feed);
    }

    //Delete
    public void deleteFeed(Long id) {
        Feed feed = feedRepository.findById(id).orElseThrow(() -> new RuntimeException("Feed not found"));
        feedRepository.delete(feed);
    }
}
