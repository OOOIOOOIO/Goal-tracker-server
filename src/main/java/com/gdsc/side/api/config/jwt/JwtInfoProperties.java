package com.gdsc.side.api.config.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:/application.yml")
public class JwtInfoProperties {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expireMin}")
    private Long expireMin;
    @Value("${jwt.refreshExpireMin}")
    private Long refreshExpireMin;

}
