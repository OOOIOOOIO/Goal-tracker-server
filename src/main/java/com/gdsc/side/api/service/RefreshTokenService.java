package com.gdsc.side.api.service;

import com.gdsc.side.api.config.jwt.JwtInfoProperties;
import com.gdsc.side.api.domain.RefreshToken;
import com.gdsc.side.api.exception.type.TokenRefreshException;
import com.gdsc.side.api.repository.RefreshTokenRepository;
import com.gdsc.side.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
//    @Value("${jwt.refreshExpireMin}")
//    private Long refreshTokenMin;

    private final JwtInfoProperties jwtInfoProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    /**
     * 리프레쉬 토큰 토큰 조회
     */
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
    /**
     * 리프레쉬 토큰 userId 조회
     */
    public Optional<RefreshToken> findByUserId(Long userId) {
        return refreshTokenRepository.findByUser_UserId(userId);
    }

    /**
     * 리프레쉬 토큰 생성 및 저장
     */
    @Transactional
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtInfoProperties.getRefreshExpireMin()));
        refreshToken.setToken(UUID.randomUUID().toString());

        // db 저장
        RefreshToken saveRefreshToken = refreshTokenRepository.save(refreshToken);

//        return refreshToken;
        return saveRefreshToken;
    }


    /**
     * 만료시간 검증
     */
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }


    /**
     * 리프레쉬 토큰 삭제
     */
    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }



}
