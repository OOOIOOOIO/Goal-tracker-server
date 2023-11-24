package com.gdsc.side.api.repository;

import com.gdsc.side.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * username & pw로 검사
     */
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    /**
     * username으로 User 조회
     */
    Optional<User> findByUsername(String username);

    /**
     * username으로 User 존재하는지 확인
     */
    Boolean existsByUsername(String username);


}
