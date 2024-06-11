package com.sparta.projectblog.dto;

import lombok.Getter;

@Getter
public class UserResponse {

    private String userid;
    private String nickname;
    private String email;
    private String content;

    public UserResponse(String userid, String nickname, String email, String content) {
        this.userid = userid;
        this.nickname = nickname;
        this.email = email;
        this.content = content;
    }
}
