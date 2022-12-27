package com.gdsc.side.api.repository;

import com.gdsc.side.api.domain.RefreshToken;
import com.gdsc.side.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUser_UserId(Long userId);

    @Modifying
    int deleteByUser(User user);

    Boolean existsByToken(String token);


}
