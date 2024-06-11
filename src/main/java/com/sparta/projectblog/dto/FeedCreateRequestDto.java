package com.sparta.projectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FeedCreateRequestDto {
    private String content;
    private Long userId;
}
