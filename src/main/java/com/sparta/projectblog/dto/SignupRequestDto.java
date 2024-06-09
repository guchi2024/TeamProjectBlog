package com.sparta.projectblog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String nickname;
    @NotBlank
    public String userId;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String email;
    private String content;
    private String status = "정상";
    private boolean admin = false;
    private String adminToken = "";

}