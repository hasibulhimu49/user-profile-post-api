package com.example.user_profile_post_api.dto.response;

import com.example.user_profile_post_api.model.entity.Profile;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    Long userId;
    String username;
    String email;
    ProfileResponseDto profile;
   // PostResponseDto post;

}
