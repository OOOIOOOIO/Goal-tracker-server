package com.gdsc.side.api.repository;

import com.gdsc.side.api.domain.ERole;
import com.gdsc.side.api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * role을 통해 권환 가져오기, enum의 값은 name이다!
     */
    Optional<Role> findByName(ERole name);
}
