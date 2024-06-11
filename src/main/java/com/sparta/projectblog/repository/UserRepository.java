package com.sparta.projectblog.repository;

import com.sparta.projectblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByUsername(String username);
    void deleteRefreshTokenByUsername(String username);


}