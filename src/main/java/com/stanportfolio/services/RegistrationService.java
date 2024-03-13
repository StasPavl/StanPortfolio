package com.stanportfolio.services;

import com.stanportfolio.data.dto.UserCredentialsDto;
import com.stanportfolio.data.user.UserProfile;
import com.stanportfolio.data.user.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserProfileRepository userProfileRepository;

    public boolean userExists(UserCredentialsDto dto){
       return userProfileRepository.findByUsernameIgnoreCase(dto.getUsername()).isPresent();
    }

    public void save(UserProfile model) {

        userProfileRepository.save(model);

    }
}
