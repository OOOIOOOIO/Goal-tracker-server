package com.gdsc.side.api.controller;

import com.gdsc.side.api.config.jwt.JwtUtils;
import com.gdsc.side.api.controller.dto.request.user.LoginRequestDto;
import com.gdsc.side.api.controller.dto.request.user.SignUpRequestDto;
import com.gdsc.side.api.controller.dto.response.auth.MessageResponseDto;
import com.gdsc.side.api.controller.dto.response.auth.ReAccessTokenResponseDto;
import com.gdsc.side.api.controller.dto.response.auth.UserInfoResponseDto;
import com.gdsc.side.api.domain.ERole;
import com.gdsc.side.api.domain.RefreshToken;
import com.gdsc.side.api.domain.Role;
import com.gdsc.side.api.domain.User;
import com.gdsc.side.api.exception.type.TokenRefreshException;
import com.gdsc.side.api.exception.type.UserNotFoundException;
import com.gdsc.side.api.repository.RoleRepository;
import com.gdsc.side.api.repository.UserRepository;
import com.gdsc.side.api.service.AuthServiceImpl;
import com.gdsc.side.api.service.RefreshTokenService;
import com.gdsc.side.api.service.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600) // 60분
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthServiceImpl authService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    /**
     * 로그인
     *
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        // 유저 검사
        if(!authService.isUserExist(loginRequestDto.getUsername(), loginRequestDto.getPassword())){
            throw new UserNotFoundException("유저가 존재하지 않습니다.");
        }

        // 인증
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));

        // SecurityContext에 올림
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 유저 정보 가져오기
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // refresh token 조회
        Optional<RefreshToken> getRefreshToken = refreshTokenService.findByUserId(userDetails.getUserId());

        // refresh token 존재한다면
        if(getRefreshToken.isPresent()){
            // 만료 시간 확인(유효할 경우 통과)
            refreshTokenService.verifyExpiration(getRefreshToken.get());

            // refreshToken 삭제
            refreshTokenService.deleteByUserId(userDetails.getUserId());
        }


        // refreshToken db 생성 및 저장
        RefreshToken refreshToken =  refreshTokenService.createRefreshToken(userDetails.getUserId());

        // jwt 생성
        String accessToken = jwtUtils.generateTokenFromUsername(userDetails.getUsername());


        /**
         * body에 유저 정보 반환
         * username
         *
         * roles
         * accessToken
         * refreshToken
         */
        return ResponseEntity.ok()
                .body(new UserInfoResponseDto(userDetails.getUserId(),
                        userDetails.getUsername(),
//                        userDetails.getEmail(),
                        roles,
                        accessToken,
                        refreshToken.getToken()));

    }


    /**
     * 회원가입(role 필수)
     *
     * 기존 사용자 이름/이메일 확인
     * 새 사용자 생성(역할을 지정하지 않은 경우 ROLE_USER 사용)
     * UserRepository를 사용하여 사용자를 데이터베이스에 저장
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {

        authService.signUp(signUpRequestDto);

        return ResponseEntity.ok(new MessageResponseDto("USER REGISTERED SUCCESSFULLY!"));

    }

    /**
     * 로그아웃
     * 리프레쉬 토큰 삭제
     */
    @PostMapping("signout")
    public ResponseEntity<?> logoutUser(){

        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principle.toString() != "anonymousUser") {
            Long userId = ((UserDetailsImpl) principle).getUserId();
            refreshTokenService.deleteByUserId(userId);
        }

        return ResponseEntity.ok()
                .body(new MessageResponseDto("You've been signed out!"));
    }

    /**
     * 리프레쉬 토큰
     */

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        // refreshToken 조회(uuid)
        String refreshToken = jwtUtils.getJwtRefreshFromHeader(request);

        // 존재한다면(시간 비교)
        if ((refreshToken != null) && (refreshToken.length() > 0)) {

            // db 조회
            return refreshTokenService.findByToken(refreshToken)
                    .map(token -> refreshTokenService.verifyExpiration(token)) // 만료시간 검증 : db에서 삭제 후 403 error
                    .map(refreshToken1 -> refreshToken1.getUser()) // 만료가 아닐 경우 그대로 토큰 리턴
                    .map(user -> { 
                        // accessToken 재생성
                        String accessToken = jwtUtils.generateTokenFromUsername(user.getUsername()); // lazy 로딩

                        return ResponseEntity.ok()
                                .body(new ReAccessTokenResponseDto("Token is refreshed successfully!", accessToken));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponseDto("Refresh Token is empty!"));
    }
}
