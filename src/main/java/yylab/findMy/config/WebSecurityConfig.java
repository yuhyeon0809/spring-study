package yylab.findMy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import yylab.findMy.service.UserService;

@RequiredArgsConstructor
@EnableWebSecurity(debug = true) //spring security 활성화
@Configuration //Bean 관련 직접 작성
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
//해당 클래스가 spring security 설정파일로 역할하기 위해 WebSecurityConfigurerAdapter 상속
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;


    // 인증을 무시할 경로 설정
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "js/**", "h2-console/**");
    }

    // http 관련 인증 설정 가능
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http
                .csrf().disable().headers().frameOptions().disable() // h2-console 화면 사용
                .and()
                    .authorizeRequests() // url 별 권한 관리 위한 시작점
                        .antMatchers("/login", "/signup", "/user", "/").permitAll() // 누구나 접근 가능
                        //.antMatchers("/").hasRole("USER") // USER, ADMIN 만 접근 가능
                        .antMatchers("/admin").hasRole("ADMIN") // ADMIN 만 접근 가능
                        .antMatchers().authenticated()
                .and()
                    .formLogin() // 로그인에 대한 설정
                        .loginPage("/login") // 로그인 페이지 링크
                        .loginProcessingUrl("/loginProcess")
                        .defaultSuccessUrl("/") // 로그인 성공시 연결되는 주소
                .and()
                    .logout() // 로그아웃 관련 설정
                    .logoutSuccessUrl("/login") //로그아웃 성공시 연결되는 주소
                    .invalidateHttpSession(true);
    }

    // 로그인 시 필요한 정보 가져옴
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) // 유저 정보를 userService에서 가져옴
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
