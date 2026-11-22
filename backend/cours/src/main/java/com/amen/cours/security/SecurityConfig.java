package com.amen.cours.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
            @Value("${app.jwt.secret}") String jwtSecret) throws Exception {

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf -> csrf.disable())

            .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    cors.setAllowedMethods(Collections.singletonList("*"));
                    cors.setAllowedHeaders(Collections.singletonList("*"));
                    cors.setExposedHeaders(Collections.singletonList("Authorization"));
                    return cors;
                }
            }))

            .authorizeHttpRequests(requests -> requests
                    .requestMatchers("/health").permitAll()
                    .requestMatchers("/api/all/**").hasAnyAuthority("ADMIN", "USER")
                    .requestMatchers(HttpMethod.GET, "/api/getbyid/**").hasAnyAuthority("ADMIN", "USER")
                    //.requestMatchers(HttpMethod.POST, "/api/addcour/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/updatecour/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/delcour/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated())

            .addFilterBefore(new JWTAuthorizationFilter(jwtSecret),
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}