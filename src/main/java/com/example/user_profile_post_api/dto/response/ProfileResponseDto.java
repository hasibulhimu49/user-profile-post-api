package com.example.user_profile_post_api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponseDto {
    Long profileId;
    String firstName;
    String lastName;
    String bio;

}
