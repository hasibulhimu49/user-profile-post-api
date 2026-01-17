package com.example.user_profile_post_api.dto.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {


    @JsonProperty("user_username")
    String username;

    @Email
    @JsonProperty("user_email")
    String email;

    @JsonProperty("user_password")
    String password;
}
