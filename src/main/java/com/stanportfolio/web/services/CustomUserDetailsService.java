package com.stanportfolio.web.services;

import com.stanportfolio.data.CustomUserDetails;
import com.stanportfolio.data.user.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private  UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var profile = userProfileRepository.findByUsernameIgnoreCase(username);
        if (profile.isPresent())
            return new CustomUserDetails(profile.get());
        throw new UsernameNotFoundException("User not found.");
    }
}
