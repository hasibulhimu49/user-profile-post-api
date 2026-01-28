package com.example.user_profile_post_api.dto.response;

import com.example.user_profile_post_api.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProfileResponseDto {
    Long profileId;
    String firstName;
    String lastName;
    Gender gender;
    String bio;
    List<PostResponseDto> posts;


    // auditing fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
