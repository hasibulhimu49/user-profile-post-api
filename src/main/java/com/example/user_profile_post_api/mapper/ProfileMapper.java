package com.example.user_profile_post_api.mapper;


import com.example.user_profile_post_api.dto.request.ProfileCreateRequestDto;
import com.example.user_profile_post_api.dto.response.ProfileResponseDto;
import com.example.user_profile_post_api.dto.update.PostUpdateRequestDto;
import com.example.user_profile_post_api.dto.update.ProfileUpdateRequestDto;
import com.example.user_profile_post_api.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    //dto to entity
    public Profile toEntity(ProfileCreateRequestDto dto)
    {
        if(dto==null)
        {
            return null;
        }
        Profile profile=new Profile();
        profile.setFirstName(dto.firstName());
        profile.setLastName(dto.lastName());
        profile.setBio(dto.bio());
        return profile;
    }

    //entity to response(dto)

    public ProfileResponseDto toResponse(Profile profile)
    {
        if(profile==null)
        {
            return null;
        }

        ProfileResponseDto responseDto=new ProfileResponseDto();
        responseDto.setProfileId(profile.getProfileId());
        responseDto.setFirstName(profile.getFirstName());
        responseDto.setLastName(profile.getLastName());
        responseDto.setBio(profile.getBio());
        return responseDto;
    }

    /**update entity from update dto*/

    public void updateEntity(ProfileUpdateRequestDto updateRequestDto, Profile profile)
    {
        if(updateRequestDto==null || profile==null)
        {
            return;
        }

        if(updateRequestDto.getFirstName()!=null)
        {
            profile.setFirstName(updateRequestDto.getFirstName());
        }

        if(updateRequestDto.getLastName()!=null)
        {
            profile.setLastName(updateRequestDto.getLastName());
        }

        if(updateRequestDto.getBio()!=null)
        {
            profile.setBio(updateRequestDto.getBio());
        }
    }
}
