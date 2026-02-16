package com.example.user_profile_post_api.security.JWT_based_Authentication.entity;

import com.example.user_profile_post_api.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails{

    User user;

    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.emptyList();
    }

    public String getPassword()
    {
        return user.getPassword();
    }

    public String getUsername()
    {
        return user.getUsername();
    }

}
