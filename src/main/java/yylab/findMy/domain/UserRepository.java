package yylab.findMy.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email); // 이메일 통해 회원 조회하기 위함
}