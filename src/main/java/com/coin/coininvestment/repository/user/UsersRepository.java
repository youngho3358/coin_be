package com.coin.coininvestment.repository.user;

import com.coin.coininvestment.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // 이메일 중복 검증
    Boolean existsByEmail(String email);
    // 닉네임 중복 검증
    Boolean existsByNickname(String nickname);
}
