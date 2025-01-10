package com.Solution.CRM.configuration;

import com.Solution.CRM.enumeration.Credentials;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    BasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/ws-chat/**").permitAll() // Allow WebSocket connections
                        .requestMatchers("/static/**", "/", "/index").permitAll()
                        .requestMatchers("/static/**", "/", "/assistant").permitAll()
                        .requestMatchers(HttpMethod.POST, "/customer/v1/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/address/v1/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/lead/v1/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/account/v1/**").authenticated()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .exceptionHandling(ex->ex.authenticationEntryPoint(basicAuthenticationEntryPoint));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername(Credentials.USER_NAME)
                .password(passwordEncoder().encode(Credentials.USER_PASSWORD))
                .build());
        return manager;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}