package com.loginAuthentication.auth.config;

import com.loginAuthentication.auth.serviceImpl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //authentication
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());

        //authorization
          http.authorizeHttpRequests(authorize -> authorize
                  .requestMatchers("/create-user").permitAll()
                  .requestMatchers("/user-dashboard").hasAnyAuthority("USER", "ADMIN")
                  .requestMatchers("/admin-dashboard").hasAuthority("ADMIN")
                  .anyRequest().authenticated());

          http.csrf(authorize -> authorize.disable());

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
         return new UserDetailsServiceImpl();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}