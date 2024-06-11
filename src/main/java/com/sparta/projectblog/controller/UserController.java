package com.sparta.projectblog.controller;

import com.sparta.projectblog.dto.UserResponse;
import com.sparta.projectblog.dto.UserUpdateRequest;
import com.sparta.projectblog.dto.SignupRequestDto;
import com.sparta.projectblog.dto.UserRequestDto;
import com.sparta.projectblog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(@RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "error";
        }

        userService.signup(requestDto);

        return "login";
    }

    @ResponseBody
    @PutMapping("/user/change-status/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userService.updateUser(id, requestDto);
    }

    @PostMapping("/logout")
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.clearContext();
        userService.deleteRefreshToken(authentication.getName());
    }
}
    @ResponseBody
    @GetMapping("")
    public UserResponse currentUserName(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.findByUsername(userDetails.getUsername()); //본인의 유저
    }
    @ResponseBody
    @PatchMapping("")
    public ResponseEntity<Void> updateprofile(@PathVariable Long id, @RequestBody UserUpdateRequest updateResponse) {
        try {
            userService.updateprofile(id, updateResponse); // 하나의 객체
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
}
