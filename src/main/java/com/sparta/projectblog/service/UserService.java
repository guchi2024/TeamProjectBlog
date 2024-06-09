package com.sparta.projectblog.service;

import com.sparta.projectblog.dto.SignupRequestDto;
import com.sparta.projectblog.dto.UserRequestDto;
import com.sparta.projectblog.entity.User;
import com.sparta.projectblog.entity.UserRoleEnum;
import com.sparta.projectblog.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    public void signup(SignupRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String nickname = requestDto.getNickname();
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();
        String content = requestDto.getContent();
        String status = requestDto.getStatus();


        // username 유효성 검사
        if (!userId.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{10,20}$")) {
            throw new IllegalArgumentException("아이디는 최소 10글자 이상, 20자 이하이며 대소문자 포함 영문 + 숫자만을 허용합니다.");
        }

        // password 유효성 검사
        if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+\\\\|\\[\\]{};:'\",.<>\\/?]).{10,}$")) {
            throw new IllegalArgumentException("비밀번호는 최소 10자 이상, 대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함해야 합니다.");
        }

        // 회원 중복 확인
        Optional<User> checkUserId = userRepository.findByUsername(userId);
        if (checkUserId.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 일치하지 않아 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(userId, nickname, username, hashedPassword, email, content, status, role);
        userRepository.save(user);
        System.out.println(user);
    }

    @Transactional
    public String updateUser(Long id, UserRequestDto requestDto) {
        Optional<User> userOptional = userRepository.findById(id);
        // 사용자가 존재하는 경우에만 상태를 변경합니다.
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String userInputPassword = requestDto.getPassword();
            String storedPassword = user.getPassword();
            if (!passwordEncoder.matches(userInputPassword, storedPassword)) {
                return "비밀번호가 일치하지 않습니다.";
            }
            if ("정상".equals(user.getStatus())) {
                user.setStatus("탈퇴");
            } else if ("탈퇴".equals(user.getStatus())) {
                throw new IllegalArgumentException("이미 탈퇴처리 된 사용자입니다.");
            }
        }
        return "정상 탈퇴 처리되었습니다.";
    }
}
