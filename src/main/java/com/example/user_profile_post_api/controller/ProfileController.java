package com.example.user_profile_post_api.controller;

import com.example.user_profile_post_api.dto.request.ProfileCreateRequestDto;
import com.example.user_profile_post_api.dto.response.ProfileResponseDto;
import com.example.user_profile_post_api.dto.update.ProfileUpdateRequestDto;
import com.example.user_profile_post_api.repository.ProfileRepository;
import com.example.user_profile_post_api.service.ProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<ProfileResponseDto> profileCreate(@Valid @RequestBody ProfileCreateRequestDto createRequestDto, @PathVariable Long userId)
    {
       ProfileResponseDto response=profileService.createProfile(userId,createRequestDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<ProfileResponseDto>> getAllProfile()
    {
        return ResponseEntity.ok(profileService.getAllProfile());

    }



    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDto> getProfileById(@PathVariable Long profileId)
    {
        return ResponseEntity.ok(profileService.getProfileById(profileId));
    }



    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDto> updateProfile(@PathVariable Long profileId , @RequestBody ProfileUpdateRequestDto updateRequestDto)
    {
        return ResponseEntity.ok(profileService.updateProfile(profileId,updateRequestDto));
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profileId)
    {
        profileService.deleteProfile(profileId);
        return ResponseEntity.noContent().build();
    }
}
