package com.gdsc.side.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
//        @UniqueConstraint(columnNames = "email")
})
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(max = 50)
    private String username;

//    @NotBlank
//    @Size(max = 50)
//    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "USER_ROLES",
    joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // daily 일 대 다, cascade,고아객체 생각해보기
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Daily> dailyList = new ArrayList<>();

    // goal 일 대 다, cascade,고아객체 생각해보기
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Goal> goalList = new ArrayList<>();


    @Builder
    public User(String username, String password) {
        this.username = username;
//        this.email = email;
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
