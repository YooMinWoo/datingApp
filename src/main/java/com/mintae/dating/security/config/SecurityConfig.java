package com.mintae.dating.security.config;

import com.mintae.dating.security.jwt.JwtFilter;
import com.mintae.dating.security.jwt.JwtUtil;
import com.mintae.dating.security.jwt.LoginFilter;
import com.mintae.dating.security.service.CustomUserDetailsService;
import com.mintae.dating.service.VerificationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final VerificationProvider randomNumberProvider;
    private String loginUrl = "/loginProcess";

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/signup", "/verification").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
//                .oauth2Login(oauth -> oauth
//                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
//                                .userService(customOAuth2UserService))
//                        .successHandler(customSuccessHandler)
//                )

                // 세션 사용 X (무상태성)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .addFilterBefore(new JwtFilter(customUserDetailsService,jwtUtil), LoginFilter.class)
//                .addFilterBefore(new CustomLogoutFilter(jwtUtil), LogoutFilter.class)
                //필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil, randomNumberProvider,loginUrl), UsernamePasswordAuthenticationFilter.class)


        ;

        return http.build();
    }
}
