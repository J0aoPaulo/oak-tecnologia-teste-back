package com.api.oak_store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll() // Permite acesso público a URLs que começam com /public/
                        .anyRequest().authenticated() // Requer autenticação para qualquer outra URL
                )
                .formLogin(form -> form
                        .loginPage("/login") // Define a página de login personalizada
                        .permitAll() // Permite acesso público à página de login
                )
                .logout(logout -> logout
                        .permitAll() // Permite logout público
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}