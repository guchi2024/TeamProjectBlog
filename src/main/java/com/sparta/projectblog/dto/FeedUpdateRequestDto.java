package com.sparta.projectblog.dto;

import java.time.LocalDateTime;

public class FeedUpdateRequestDto {
    private String content;
    private LocalDateTime updatedAt;

    public FeedUpdateRequestDto(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

}