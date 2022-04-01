package yylab.findMy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yylab.findMy.domain.UserInfo;
import yylab.findMy.domain.UserRepository;
import yylab.findMy.controller.UserInfoDto;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // UserDetailService 상속시 필수로 구현해야 하는 메소드
    // UserDetails가 기본 반환 타입, UserInfo가 이를 상속하고 있으므로 자동으로 다운캐스팅됨
    @Override
    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public Long save(UserInfoDto infoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        return userRepository.save(UserInfo.builder()
                .email(infoDto.getEmail())
                .auth(infoDto.getAuth())
                .password(infoDto.getPassword()).build()).getCode();
    }
}
