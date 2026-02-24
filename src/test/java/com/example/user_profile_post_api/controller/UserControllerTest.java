package com.example.user_profile_post_api.controller;

import com.example.user_profile_post_api.dto.response.UserResponseDto;
import com.example.user_profile_post_api.mapper.PostMapper;
import com.example.user_profile_post_api.mapper.UserMapper;
import com.example.user_profile_post_api.security.JWT_based_Authentication.jwt.JwtAuthenticationFilter;
import com.example.user_profile_post_api.security.JWT_based_Authentication.jwt.JwtService;
import com.example.user_profile_post_api.security.JWT_based_Authentication.service.CustomUserDetailsService;
import com.example.user_profile_post_api.service.PostService;
import com.example.user_profile_post_api.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PostService postService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private PostMapper postMapper;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void shouldReturnUserWhenUserIdExists() throws Exception {

        // Given
        UserResponseDto dto = new UserResponseDto(1L, "himu", "himu@gmail.com", null);
        when(userService.getUserById(1L)).thenReturn(dto);

        // When & Then
        mockMvc.perform(get("/api/users/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("himu"))
                .andExpect(jsonPath("$.email").value("himu@gmail.com"))
                .andExpect(jsonPath("$.userId").value(1));
    }
}