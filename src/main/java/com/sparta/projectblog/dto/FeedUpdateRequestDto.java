package com.sparta.projectblog.dto;

import java.time.LocalDateTime;

public class FeedUpdateRequestDto {
    private String content;
    private LocalDateTime updatedAt;

    public FeedUpdateRequestDto(String content, LocalDateTime updatedAt) {
        this.content = content;
        this.updatedAt = updatedAt;
    }
    public String getContent() {
        return content;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }


}


