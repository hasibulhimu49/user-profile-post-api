package com.example.user_profile_post_api.security.JWT_based_Authentication.controller;


import com.example.user_profile_post_api.dto.request.UserCreateRequestDto;
import com.example.user_profile_post_api.model.entity.User;
import com.example.user_profile_post_api.repository.UserRepository;
import com.example.user_profile_post_api.security.JWT_based_Authentication.dto.request.LoginRequest;
import com.example.user_profile_post_api.security.JWT_based_Authentication.entity.CustomUserDetails;
import com.example.user_profile_post_api.security.JWT_based_Authentication.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth/api")
@AllArgsConstructor
public class AuthController {
    UserRepository repository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody UserCreateRequestDto dto)
    {
        User user=new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));

        repository.save(user);
        return "Save Successfull";

    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = repository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        // Generate token
        String token = jwtService.generateToken(new CustomUserDetails(user));

        return ResponseEntity.ok(new HashMap<>() {{
            put("message", "Login success");
            put("token", token);
        }});


        /*
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Login success");
        map.put("token", token);

         */
    }



    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout successful");
    }


}
