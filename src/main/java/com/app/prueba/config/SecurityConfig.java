package com.app.prueba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    AnonymousFilter anonymousFilter;

    @Autowired
    AuthenticatedFilter authenticatedFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((requests) -> requests
                // Swagger
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()

                // API
                .requestMatchers(HttpMethod.GET, "/", "/api/users", "/api/users/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/", "/api/users", "/api/users/**", "/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/", "/api/users", "/api/users/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/", "/api/users", "/api/users/**").permitAll())

                // GUARDS
                .addFilterBefore(anonymousFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticatedFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }
}