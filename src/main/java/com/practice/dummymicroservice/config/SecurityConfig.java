package com.practice.dummymicroservice.config;

import com.practice.dummymicroservice.security.AuthTokenFilter;
import com.practice.dummymicroservice.security.HandlerAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final AuthTokenFilter authTokenFilter;

    @Bean // (1)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // (2)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean // (3)
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean // (4)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .headers(headers -> {
                    headers
                            .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable());
                })
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())


                // Add Paseto token filter
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())

                // Set unauthorized requests exception handler
                .exceptionHandling( exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new HandlerAuthenticationEntryPoint())
                        .accessDeniedHandler(new HandlerAccessDeniedHandler())
                        )
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Set permissions on endpoints
                .authorizeHttpRequests(authz -> { authz
                        .requestMatchers(new AntPathRequestMatcher("/api/account/authenticate")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/account/register")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/account/register/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher( "/**","OPTIONS")).permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher("/configuration/ui"),
                                new AntPathRequestMatcher("/swagger-resources/**"),
                                new AntPathRequestMatcher("/configuration/security"),
                                new AntPathRequestMatcher("/webjars/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/**")).authenticated();
                });

        // @formatter:on
        return http.build();
    }

}