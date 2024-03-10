package com.stanportfolio.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("spring.datasource")
public class DatabaseConfiguration {

    private String url;
    private String driverClassName;
    private String userName;
    private String password;
}
