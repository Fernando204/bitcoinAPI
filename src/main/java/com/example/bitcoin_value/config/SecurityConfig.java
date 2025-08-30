package com.example.bitcoin_value.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // desativa CSRF (necessário p/ POST via Postman)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/bitcoin/**").permitAll() // libera sua API
                .anyRequest().permitAll() // libera tudo
            );

        return http.build();
    }
}