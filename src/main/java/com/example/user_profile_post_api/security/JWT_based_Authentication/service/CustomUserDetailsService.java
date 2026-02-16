package com.example.user_profile_post_api.security.JWT_based_Authentication.service;


import com.example.user_profile_post_api.model.entity.User;
import com.example.user_profile_post_api.repository.UserRepository;
import com.example.user_profile_post_api.security.JWT_based_Authentication.entity.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    public UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=repository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
