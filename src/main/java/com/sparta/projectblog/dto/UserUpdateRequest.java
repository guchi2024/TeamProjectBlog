package com.sparta.projectblog.dto;


import lombok.Getter;


@Getter
public class UserUpdateRequest {
    private String nickname;
    private String email;
    private String content;
    private String password;

    public UserUpdateRequest(String nickname, String email, String content, String password) {
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.password = password;
    }
}
