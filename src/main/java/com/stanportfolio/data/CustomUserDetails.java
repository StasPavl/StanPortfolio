package com.stanportfolio.data;

import com.stanportfolio.data.user.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class CustomUserDetails implements UserDetails {
    private final UserProfile userProfile;

    public CustomUserDetails(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String getPassword() {
        return userProfile.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return userProfile.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<SimpleGrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        var roleDeveloper = new SimpleGrantedAuthority("ROLE_DEVELOPER");
        var roleOwner = new SimpleGrantedAuthority("ROLE_OWNER");
        if (Objects.equals("lichfb",userProfile.getUsername().toLowerCase())){
            authorities.add(roleDeveloper);
            authorities.add(roleOwner);
        }

        return authorities;
    }
}
