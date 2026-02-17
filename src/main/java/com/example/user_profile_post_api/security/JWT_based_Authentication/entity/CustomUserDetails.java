package com.example.user_profile_post_api.security.JWT_based_Authentication.entity;

import com.example.user_profile_post_api.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails{

    User user;
    public User getUser() {
        return this.user;
    }


    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        //return Collections.emptyList();
        return List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
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
