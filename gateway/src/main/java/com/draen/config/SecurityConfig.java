package com.draen.config;

import com.draen.security.data.user.entity.UserRole;
import com.draen.security.jwt.AuthEntryPoint;
import com.draen.security.jwt.AuthJwtFilter;
import com.draen.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackages = "com.draen.security.data.user.repository")
public class SecurityConfig {
    @Value("${custom.security.urls.permit}")
    private String[] permitUrls;

    @Value("${custom.security.urls.abonent}")
    private String[] abonentUrls;

    @Value("${custom.security.urls.manager}")
    private String[] managerUrls;

    private final AuthEntryPoint authEntryPoint;

    public SecurityConfig(AuthEntryPoint authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public AuthJwtFilter authJwtFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        return new AuthJwtFilter(jwtUtils, userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthJwtFilter authJwtFilter) throws Exception {
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeHttpRequests()
                .requestMatchers(managerUrls).hasAuthority(UserRole.MANAGER.name())
                .requestMatchers(abonentUrls).hasAuthority(UserRole.ABONENT.name())
                .requestMatchers(permitUrls).permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authJwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authEntryPoint);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        DelegatingPasswordEncoder encoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        encoder.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder);
        return encoder;
    }

}
