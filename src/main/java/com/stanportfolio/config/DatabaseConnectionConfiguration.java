package com.stanportfolio.config;

import com.stanportfolio.utils.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DatabaseConfiguration.class)
public class DatabaseConnectionConfiguration {

    private final DatabaseConfiguration databaseConfiguration;

    @Profile("dev")
    @Bean
    public String getDevConnectionConfig(){
        Logger.getLogger().printMessage("Configuration for Dev");
        var message = "URL: %s%nDriver Class Name: %s%nUsername: %s%nPassword: %s%n".formatted(
                databaseConfiguration.getUrl(),
                databaseConfiguration.getDriverClassName(),
                databaseConfiguration.getUserName(),
                databaseConfiguration.getPassword()
        );
        Logger.getLogger().printMessage(message);
        return "Database configured for Development.";
    }
    @Profile("default")
    @Bean
    public String getDefaultConnectionConfig(){
        Logger.getLogger().printMessage("Configuration for Dev");
        var message = "URL: %s%nDriver Class Name: %s%nUsername: %s%nPassword: %s%n".formatted(
                databaseConfiguration.getUrl(),
                databaseConfiguration.getDriverClassName(),
                databaseConfiguration.getUserName(),
                databaseConfiguration.getPassword()
        );
        Logger.getLogger().printMessage(message);
        return "Database configured for Production.";
    }
    @Profile("prod")
    @Bean
    public String getProdConnectionConfig(){
        Logger.getLogger().printMessage("Configuration for Def");
        var message = "URL: %s%nDriver Class Name: %s%nUsername: %s%nPassword: %s%n".formatted(
                databaseConfiguration.getUrl(),
                databaseConfiguration.getDriverClassName(),
                databaseConfiguration.getUserName(),
                databaseConfiguration.getPassword()
        );
        Logger.getLogger().printMessage(message);
        return "Database configured for Default.";
    }

}
