/*
package com.example.user_profile_post_api.security.Session_based_Authentication.controller;

import com.example.user_profile_post_api.dto.request.UserCreateRequestDto;
import com.example.user_profile_post_api.model.entity.User;
import com.example.user_profile_post_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    UserRepository repository;
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody UserCreateRequestDto dao)
    {
        User user = new User();
        user.setUsername(dao.username());
        user.setEmail(dao.email());
        user.setPassword(passwordEncoder.encode(dao.password()));

        repository.save(user);

        return "User Create Successfully";
    }

}
*/