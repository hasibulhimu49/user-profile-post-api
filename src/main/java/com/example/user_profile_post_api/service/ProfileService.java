package com.example.user_profile_post_api.service;

import com.example.user_profile_post_api.dto.request.ProfileCreateRequestDto;
import com.example.user_profile_post_api.dto.response.PostResponseDto;
import com.example.user_profile_post_api.dto.response.ProfileResponseDto;
import com.example.user_profile_post_api.dto.update.ProfileUpdateRequestDto;
import com.example.user_profile_post_api.entity.Profile;
import com.example.user_profile_post_api.entity.User;
import com.example.user_profile_post_api.mapper.ProfileMapper;
import com.example.user_profile_post_api.repository.ProfileRepository;
import com.example.user_profile_post_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserRepository userRepository;

    //Profile Create
    public ProfileResponseDto createProfile(Long userId, ProfileCreateRequestDto dto)
    {
        User user=userRepository.findById(userId).
                orElseThrow(()->new EntityNotFoundException("Not found"));

        Profile profile=profileMapper.toEntity(dto);
        profile.setUser(user); //relationship handled here
        Profile savedProfile=profileRepository.save(profile);
        return profileMapper.toResponse(savedProfile);

    }

    //get profile by id
    public ProfileResponseDto getProfileById(Long profildeId)
    {
       /*Optional<Profile> optionalProfile=profileRepository.findById(profildeId);
       if(optionalProfile.isPresent())
       {
           Profile profile= optionalProfile.get();
           return profileMapper.toResponse(profile);
       }
       else
       {
           throw new EntityNotFoundException("not found");
       }*/

        Profile profile=profileRepository.findById(profildeId).
                orElseThrow(()->new EntityNotFoundException("Not found"));
        return profileMapper.toResponse(profile);
    }

    //get all profile
    public List<ProfileResponseDto> getAllProfile()
    {
        List <Profile> profiles=profileRepository.findAll();
        return profiles.stream().map(profile -> profileMapper.toResponse(profile)).toList();
    }

    //update profile

    public ProfileResponseDto updateProfile(Long profileId,ProfileUpdateRequestDto updateRequestDto)
    {
        Profile profile=profileRepository.findById(profileId).orElseThrow(()->new EntityNotFoundException("Not found"));
        profileMapper.updateEntity(updateRequestDto,profile);
        profileRepository.save(profile);
        return profileMapper.toResponse(profile);

    }
    //delete profile

    public void deleteProfile(Long profileId)
    {
        if(!profileRepository.existsById(profileId))
        {
            throw new EntityNotFoundException("Not found");
        }
        else {
            profileRepository.deleteById(profileId);
        }
    }
}
