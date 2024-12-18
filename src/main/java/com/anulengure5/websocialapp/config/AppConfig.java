package com.anulengure5.websocialapp.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{

      // Server is stateless session , rather than storing session in server , web Token in local storage manages the user session status
        http.sessionManagement(management->management.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize->Authorize
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()).
                addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf->csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigurationSource()));
//                                                                            /api/** endpoints are spring security protected
//        remaining are public

         return http.build();

    }

    private CorsConfigurationSource corsConfigurationSource() {

        return new CorsConfigurationSource() {

            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
               CorsConfiguration cfg=new CorsConfiguration() ;
               cfg.setAllowedOrigins(Arrays.asList(
                    "https://localhost:3000/"
               ));

               cfg.setAllowedMethods(Collections.singletonList("*"));
               cfg.setAllowCredentials(true);
               cfg.setAllowedHeaders(Collections.singletonList("*"));
               cfg.setExposedHeaders(Arrays.asList("Authorization"));
                cfg.setMaxAge(3600L);

                return cfg;
            }
        };

    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}