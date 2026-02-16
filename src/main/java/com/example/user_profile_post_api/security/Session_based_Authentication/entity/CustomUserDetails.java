/*package com.example.user_profile_post_api.security.Session_based_Authentication.entity;

import com.example.user_profile_post_api.model.entity.User;
import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails{

    private final User user; // Database থেকে fetch করা User entity

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // role নেই
    }

    @Override
    public String getUsername() {
        System.out.println("UserDetailsPrint:"+user.getUsername());
        return user.getUsername(); // ✅ ঠিক
    }

    @Override
    public String getPassword() {
        System.out.println("UserDetailsPrint:"+user.getPassword());
        return user.getPassword(); // ✅ অবশ্যই DB থেকে encoded password return করতে হবে
    }
}
*/