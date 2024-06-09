package com.sparta.projectblog.config;
import com.sparta.projectblog.jwt.JwtUtil;
import com.sparta.projectblog.jwt.LoginFilter;
import com.sparta.projectblog.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.csrf((csrf) -> csrf.disable());
        http.formLogin((auth) -> auth.disable());
        http.httpBasic((auth) -> auth.disable());

        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
                        .requestMatchers("/api/user/**").permitAll() // '/api/user/'로 시작하는 요청 모두 접근 허가
                        .anyRequest().authenticated() // 그 외 모든 요청 인증처리
        );

        http.formLogin((formLogin) ->
                formLogin
                        .loginPage("/api/user/login-page").permitAll()
        );


        // 접근 불가 페이지
        http.exceptionHandling((exceptionHandling) ->
                exceptionHandling
                        // "접근 불가" 페이지 URL 설정
                        .accessDeniedPage("/forbidden.html")
        );

        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, userRepository), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}