package com.gdsc.side.api.service;


import com.gdsc.side.api.controller.dto.request.user.SignUpRequestDto;
import com.gdsc.side.api.controller.dto.response.auth.MessageResponseDto;
import com.gdsc.side.api.domain.ERole;
import com.gdsc.side.api.domain.Role;
import com.gdsc.side.api.domain.User;
import com.gdsc.side.api.exception.type.DuplicateUsernameException;
import com.gdsc.side.api.exception.type.UserNotFoundException;
import com.gdsc.side.api.repository.RoleRepository;
import com.gdsc.side.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public boolean isUserExist(String username, String password) {

        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);

        if(user.isPresent()) return true;

        return false;
    }

    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {
        // 유효성 검사
        if (userRepository.existsByUsername(signUpRequestDto.getUsername())) {
            throw new DuplicateUsernameException("중복된 username 존재합니다.");
        }
//        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
//            return ResponseEntity.badRequest().body(new MessageResponseDto("Error: Email is already in use!"));
//        }

        // 유저 생성
        User user = new User(signUpRequestDto.getUsername(),
//                signUpRequestDto.getEmail(),
                encoder.encode(signUpRequestDto.getPassword()));

        Set<String> strRoles = signUpRequestDto.getRole();

        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("ERROR : ROLE IS NOT FOUND"));

            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    }
}
