package com.example.user_profile_post_api.service;

import com.example.user_profile_post_api.dto.request.UserCreateRequestDto;
import com.example.user_profile_post_api.dto.response.UserResponseDto;
import com.example.user_profile_post_api.mapper.UserMapper;
import com.example.user_profile_post_api.model.entity.User;
import com.example.user_profile_post_api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  UserMapper userMapper;

    @InjectMocks
    public UserService userService;


    @Test
    void shouldUserCreateSuccessfully()
    {
        //Given  → setup
        UserCreateRequestDto requestDto=new UserCreateRequestDto("Hasibul","hasibul@gmail.com","12345");

        User user=new User();
        user.setUsername("Hasibul");

        User savedUser=new User();
        savedUser.setUsername("Hasibul");

        UserResponseDto responseDto=new UserResponseDto();
        responseDto.setUsername("Hasibul");
        responseDto.setEmail("hasibul@gmail.com");


        when(userMapper.toEntity(requestDto)).thenReturn(user); //dto দিলে user দিবে
        when(userRepository.save(any(User.class))).thenReturn(savedUser); //save করলে savedUser দিবে
        when(userMapper.toResponse(savedUser)).thenReturn(responseDto); //save করলে savedUser দিবে

        // When → method call
        UserResponseDto result=userService.createUser(requestDto);

        // Then → verify
        assertEquals("Hasibul",result.getUsername());
    }

    @Test
    void shouldUserGetByIdSuccessfully()
    {
        // Given
        Long userId=1L;
        User user=new User();
        user.setUserId(userId);
        user.setUsername("Himu");

        UserResponseDto responseDto=new UserResponseDto();
        responseDto.setUsername("Himu");

        // Mock repository behavior
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toResponse(user)).thenReturn(responseDto);

        // When
        UserResponseDto result=userService.getUserById(userId);

        // Then
        assertEquals("Himu",result.getUsername());

    }
}
