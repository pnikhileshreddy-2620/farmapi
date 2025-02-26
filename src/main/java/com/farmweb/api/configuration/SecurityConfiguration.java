package com.farmweb.api.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/customer/**", "/api/vendor/**","/api/admins/**").permitAll() // Allow unauthenticated access to these endpoints
//                        .requestMatchers("/api/admins/save").hasRole("ADMIN")
                        .anyRequest().authenticated()  // Require authentication for all other endpoints
                )
                .httpBasic(withDefaults()); // Enable HTTP Basic Authentication for simplicity; consider more robust methods for production

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

