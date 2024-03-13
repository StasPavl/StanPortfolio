package com.stanportfolio.controllers;

import com.stanportfolio.data.dto.UserCredentialsDto;
import com.stanportfolio.data.user.UserProfile;
import com.stanportfolio.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/newuser")
    public String registerNewUser(@RequestBody UserCredentialsDto userCredentials){
        if (Objects.isNull(userCredentials))
            return "redirect:/register?badRequest";

        if (registrationService.userExists(userCredentials))
            return "redirect:/register?userExists";

        if (!Objects.equals(userCredentials.getPassword(), userCredentials.getRePassword()))
            return "redirect:/register?passMissMatch";

        var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        var now = LocalDateTime.now();

        var model = new UserProfile();
            model.setUsername(userCredentials.getUsername());
            model.setEmail(userCredentials.getEmail());
            model.setEncryptedPassword(new BCryptPasswordEncoder().encode(userCredentials.getPassword()));
            model.setCreatedOn(dtf.format(now));
            model.setLastUpdated(model.getCreatedOn());
        registrationService.save(model);
        return "redirect:/home";

    }
}
