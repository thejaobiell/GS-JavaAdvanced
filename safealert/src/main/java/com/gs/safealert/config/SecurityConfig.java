package com.gs.safealert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // desativa CSRF para facilitar testes com Postman
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // permite tudo sem autenticação
            )
            .httpBasic(Customizer.withDefaults()); // pode ser removido também se quiser desativar auth básica

        return http.build();
    }
}
