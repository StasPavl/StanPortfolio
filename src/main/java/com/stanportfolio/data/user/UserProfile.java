package com.stanportfolio.data.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long uid;

    private String username;
    private String encryptedPassword;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String createdOn;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String lastUpdated;
}
