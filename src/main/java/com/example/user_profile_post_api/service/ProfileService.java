package com.example.user_profile_post_api.service;

import com.example.user_profile_post_api.dto.request.ProfileCreateRequestDto;
import com.example.user_profile_post_api.dto.response.ProfileResponseDto;
import com.example.user_profile_post_api.dto.update.ProfileUpdateRequestDto;
import com.example.user_profile_post_api.model.entity.Profile;
import com.example.user_profile_post_api.model.entity.User;
import com.example.user_profile_post_api.mapper.ProfileMapper;
import com.example.user_profile_post_api.model.enums.Gender;
import com.example.user_profile_post_api.repository.ProfileRepository;
import com.example.user_profile_post_api.repository.UserRepository;
import com.example.user_profile_post_api.repository.projection.ProfileSummary;
import com.example.user_profile_post_api.repository.specification.ProfileSpecifications;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ProfileResponseDto createProfile(Long userId, ProfileCreateRequestDto dto) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new EntityNotFoundException("Not found"));

        Profile profile = profileMapper.toEntity(dto);
        profile.setUser(user); //relationship handled here
        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.toResponse(savedProfile);
    }

    //get profile by id
    public ProfileResponseDto getProfileById(Long profildeId) {
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

        Profile profile = profileRepository.findById(profildeId).
                orElseThrow(() -> new EntityNotFoundException("Not found"));

        return profileMapper.toResponse(profile);
    }


    //get all profile
    @Transactional
    public List<ProfileResponseDto> getAllProfile() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(profile -> profileMapper.toResponse(profile)).toList();
    }


    //update profile

    public ProfileResponseDto updateProfile(Long profileId, ProfileUpdateRequestDto updateRequestDto) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new EntityNotFoundException("Not found"));


        // STEP 1: Check ownership BEFORE updating
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!profile.getUser().getUsername().equals(loggedInUsername)) {
            throw new AccessDeniedException("You can update only your own profile");
        }


        profileMapper.updateEntity(updateRequestDto, profile);
        profileRepository.save(profile);

        return profileMapper.toResponse(profile);

    }
    //delete profile

    public void deleteProfile(Long profileId) {
        if (!profileRepository.existsById(profileId)) {
            throw new EntityNotFoundException("Not found");
        } else {
            profileRepository.deleteById(profileId);
        }
    }


    public List<ProfileResponseDto> getProfileByFirstnameAndGender(String firstname, Gender gender) {
        return profileRepository.findByFirstNameAndGender(firstname, gender)
                .stream().map(profile -> profileMapper.toResponse(profile)).toList();
    }


    public Optional<ProfileResponseDto> findByUsername(String username) {
        return profileRepository.findByUsername(username).map(p -> profileMapper.toResponse(p));
    }


    public List<ProfileResponseDto> searchProfileByBio(String bio) {
        return profileRepository.searchProfileByBio(bio).stream().map(profile -> profileMapper.toResponse(profile)).toList();
    }


    //paging
    public Page<ProfileResponseDto> searchByFirstName(String keyword, Pageable pageable) {
        return profileRepository.findByFirstNameContaining(keyword, pageable).map(profileMapper::toResponse);
    }



    //Projection
    public List<ProfileSummary> searchByLastname(String lastname)
    {
        return profileRepository.findByLastName(lastname);
    }


    //Specifications (Dynamic Search)
    public Page<ProfileResponseDto> search(String firstname,String lastname,
                                           String bio,Gender gender,String username,
                                           Pageable pageable)
    {
        Specification<Profile> spec=Specification.allOf(ProfileSpecifications.
                hasFirstName(firstname)).and(ProfileSpecifications.hasLastName(lastname))
                .and(ProfileSpecifications.hasBio(bio)).and(ProfileSpecifications.
                        hasGender(gender)).and(ProfileSpecifications.hasUsername(username));

        return profileRepository.findAll(spec,pageable).map(profileMapper::toResponse);
    }
}
