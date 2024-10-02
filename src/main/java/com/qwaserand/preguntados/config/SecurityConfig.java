package com.qwaserand.preguntados.config;

import com.qwaserand.preguntados.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    /**
     * Este metodo tendra la cadena de filtros que se ira ejecutando
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .csrf(csrf -> csrf //
                        .disable())

                .authorizeHttpRequests(authRequest -> authRequest

                        // Se permite el acceso publico a /auth/**
                        .requestMatchers("/auth/**", "//api/v1/preguntados").permitAll() //

                        // Para acceder al resto de las rutas se debera estar autenticado
                        .anyRequest().authenticated() //
                )

                .sessionManagement(sessionManager -> sessionManager //
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authenticationProvider(authProvider) //

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //

                .build();

    }

}
