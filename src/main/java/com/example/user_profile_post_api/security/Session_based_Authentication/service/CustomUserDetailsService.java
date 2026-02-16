/*
package com.example.user_profile_post_api.security.Session_based_Authentication.service;


import com.example.user_profile_post_api.model.entity.User;
import com.example.user_profile_post_api.repository.UserRepository;
import com.example.user_profile_post_api.security.Session_based_Authentication.entity.CustomUserDetails;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("Found user in DB: " + user.getUsername() + ", password: " + user.getPassword());

        return new CustomUserDetails(user);
    }


}
*/