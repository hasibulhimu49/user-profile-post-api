package com.example.user_profile_post_api.service;

import com.example.user_profile_post_api.dto.request.UserCreateRequestDto;
import com.example.user_profile_post_api.dto.response.UserResponseDto;
import com.example.user_profile_post_api.model.entity.User;
import com.example.user_profile_post_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    void shouldSaveUserSuccessfully() {

        // Given
        UserCreateRequestDto dto = UserCreateRequestDto.builder()
                .username("Hasibul")
                .password("11772244")
                .email("hasibul@gmail.com")
                .build();

        // When
        UserResponseDto response = userService.createUser(dto);

        // Then
        assertNotNull(response);
        assertEquals("Hasibul", response.getUsername());

        // 🔥 Verify from DB
        User dbUser = userRepository.findByUsername("Hasibul")
                .orElse(null);

        assertNotNull(dbUser);
        assertEquals("Hasibul", dbUser.getUsername());
    }

}
