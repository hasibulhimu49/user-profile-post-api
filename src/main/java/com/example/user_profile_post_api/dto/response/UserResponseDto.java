package com.example.user_profile_post_api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    Long userId;
    String username;
    String email;

}
