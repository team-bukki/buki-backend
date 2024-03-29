package com.example.skptemp.domain.user.repository;

import com.example.skptemp.domain.user.entity.User;
import com.example.skptemp.global.constant.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginTypeAndAuthProviderId(LoginType loginType, String authProviderId);
    Optional<User> findByCode(String code);
}
