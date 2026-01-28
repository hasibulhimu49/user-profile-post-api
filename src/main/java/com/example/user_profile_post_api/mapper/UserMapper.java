package com.example.user_profile_post_api.mapper;

import com.example.user_profile_post_api.dto.request.UserCreateRequestDto;
import com.example.user_profile_post_api.dto.response.ProfileResponseDto;
import com.example.user_profile_post_api.dto.response.UserResponseDto;
import com.example.user_profile_post_api.dto.update.UserUpdateRequestDto;
import com.example.user_profile_post_api.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /* Create DTO → Entity */
    public User toEntity(UserCreateRequestDto dto)
    {
        if(dto==null)
        {
            return null;
        }

        User user=new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }

    /* Entity → Response DTO */

    public UserResponseDto toResponse(User user)
    {
        if(user==null)
        {
            return null;
        }

        UserResponseDto responseDto=new UserResponseDto();
        responseDto.setUserId(user.getUserId());
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());


        // 🔥 THIS LINE CAUSES N+1
        if (user.getProfile() != null) {
            ProfileResponseDto profileDto = new ProfileResponseDto();
            profileDto.setFirstName(user.getProfile().getFirstName());
            profileDto.setLastName(user.getProfile().getLastName());
            profileDto.setBio(user.getProfile().getBio());
            profileDto.setGender(user.getProfile().getGender());

            responseDto.setProfile(profileDto);
        }

        return responseDto;
    }


    /* Update Entity from Update DTO */
    public void updateEntity(UserUpdateRequestDto updateRequestDto,User user) {
        if (user == null || updateRequestDto == null) {
            return;
        }

        if (updateRequestDto.getUsername() != null) {
            user.setUsername(updateRequestDto.getUsername());
        }

        if (updateRequestDto.getEmail() != null) {
            user.setEmail(updateRequestDto.getEmail());
        }
        if (updateRequestDto.getPassword() != null) {
            user.setPassword(updateRequestDto.getPassword());
        }
    }

}
