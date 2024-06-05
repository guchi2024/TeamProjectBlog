package com.sparta.projectblog.dto;

public class FeedUpdateRequestDto {
    private String content;

    public FeedUpdateRequestDto() {}

    public FeedUpdateRequestDto(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
