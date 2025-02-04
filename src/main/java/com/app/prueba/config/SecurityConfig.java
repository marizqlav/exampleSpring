package com.app.prueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.GET, "/", "api/users", "api/users/").permitAll()
                .requestMatchers(HttpMethod.POST, "/", "api/users", "api/auth/*").permitAll()
                .requestMatchers(HttpMethod.PUT, "/", "api/users/").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/", "api/users/*").permitAll());

        return http.build();
    }

}
