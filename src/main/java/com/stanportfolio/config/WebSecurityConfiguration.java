package com.stanportfolio.config;

import com.stanportfolio.web.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                                //Pages
                                .requestMatchers(
                                        "/", "/home", "/index","/osby","/register").permitAll()
                                //Resources
                                .requestMatchers("/api/v1/registration/**").permitAll()
                                //Administration
                                .requestMatchers("/css/**", "/images/**", "/webjars/**").permitAll()
                                //Administration
                                .requestMatchers("/acp/**").hasAnyRole("DEVELOPER","OWNER")
                                //User-related
                                .requestMatchers("/profile").hasRole("USER")
                                //anything else
                                .anyRequest().authenticated());
            //set up Spring Default Login Page
        httpSecurity.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout((logout) -> logout.addLogoutHandler(new HeaderWriterLogoutHandler(
                        new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.COOKIES))));

        httpSecurity.headers(configurer -> configurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

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
