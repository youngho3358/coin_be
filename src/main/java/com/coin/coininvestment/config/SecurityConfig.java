package com.coin.coininvestment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { // BCrypt 를 사용한 비밀번호 암호화 메소드

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // 시큐리티 설정

        // csrf disable
        http
                .csrf((auth) -> auth.disable());

        // form 로그인 방식 비활성화
        http
                .formLogin((auth) -> auth.disable());

        // http basic 인증 방식 비활성화
        http
                .httpBasic((auth) -> auth.disable());

        // 경로 별 인가 설정
        http
                .authorizeHttpRequests((auth) -> auth
                        // 로그인, 메인, 회원가입 경로 권한 없는 사용자 인가
                        .requestMatchers("/login", "/", "/join/user").permitAll()
                        // admin 페이지 ADMIN 사용자만 인가
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // 이외의 모든 페이지는 로그인한 사용자만 인가
                        .anyRequest().authenticated());

        // 세션 설정 (JWT 를 사용하는 경우 세션으로 사용자를 관리하지 않기 때문에 STATELESS 상태로 변경)
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
