package com.stanportfolio.config;

import com.stanportfolio.web.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests(request -> {
                    try {
                        request
                                .requestMatchers(
                                        "/",
                                        "/home",
                                        "/index",
                                        "/css/**",
                                        "/images/**",
                                        "/osby",
                                        "/register",
                                        "/api/v1/registration/**").permitAll()
                                .requestMatchers("/acp/**").hasAnyRole("DEVELOPER","OWNER")
                                .requestMatchers("/profile").hasRole("USER")
                                .anyRequest().authenticated()
                                .and()
                                    .formLogin().permitAll()
                                .and()
                                    .logout().permitAll();

                    } catch (Exception e){
                        throw new RuntimeException(e);
                    }
                });
        httpSecurity.headers().frameOptions().disable();

        return httpSecurity.build();
    }
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new CustomUserDetailsService();
    }
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        var authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(getUserDetailsService());
            authProvider.setPasswordEncoder(getBCryptPasswordEncoder());
        return authProvider;
    }
}
