package com.stanportfolio.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentialsDto {

    private String username;
    private String email;
    private String password;
    private String rePassword;
}
