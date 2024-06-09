package com.sparta.projectblog.entity;

import com.sparta.projectblog.entity.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String content;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = true, length = 1000)
    private String refreshToken;


    public User(String userId, String nickname, String username, String hashedPassword, String email, String content, String status, UserRoleEnum role) {
        this.userId = userId;
        this.nickname = nickname;
        this.username = username;
        this.password = hashedPassword;
        this.email = email;
        this.content = content;
        this.status = status;
        this.role = role;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


}