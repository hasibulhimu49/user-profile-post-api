package com.example.user_profile_post_api.service;

import com.example.user_profile_post_api.dto.request.ProfileCreateRequestDto;
import com.example.user_profile_post_api.dto.response.ProfileResponseDto;
import com.example.user_profile_post_api.mapper.ProfileMapper;
import com.example.user_profile_post_api.model.entity.Profile;
import com.example.user_profile_post_api.model.entity.User;
import com.example.user_profile_post_api.model.enums.Gender;
import com.example.user_profile_post_api.repository.ProfileRepository;
import com.example.user_profile_post_api.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileMapper profileMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProfileService profileService;

    @Test
    @DisplayName("Should create profile successfully when user exists")
    void shouldProfileCreateSuccessfully()
    {
        // -------- Given --------
        Long userId = 1L;

        User user = new User();
        user.setUserId(userId);

        ProfileCreateRequestDto requestDto = new ProfileCreateRequestDto("Hasib", "Khan", Gender.MALE, "Hasib name to sunai hoga");

        Profile profile = new Profile();
        Profile savedProfile = new Profile();

        ProfileResponseDto responseDto = new ProfileResponseDto();
        responseDto.setFirstName("Hasib");
        responseDto.setLastName("Khan");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(profileMapper.toEntity(requestDto)).thenReturn(profile);
        when(profileRepository.save(profile)).thenReturn(savedProfile);
        when(profileMapper.toResponse(savedProfile)).thenReturn(responseDto);


        // -------- When --------
        ProfileResponseDto result = profileService.createProfile(userId, requestDto);


        // -------- Then --------
        assertNotNull(result);
        assertEquals("Hasib", result.getFirstName());
        assertEquals("Khan", result.getLastName());

        verify(userRepository).findById(userId);
        verify(profileRepository).save(profile);
        verify(profileMapper).toEntity(requestDto);
        verify(profileMapper).toResponse(savedProfile);
    }
}
