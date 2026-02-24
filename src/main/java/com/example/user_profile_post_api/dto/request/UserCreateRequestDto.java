package com.example.user_profile_post_api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
public record UserCreateRequestDto(

        @NotNull(message = "User name can no be null")
        @JsonProperty("user_username")
        String username,

        @Email()
        @JsonProperty("user_email")
        String email,

        @JsonProperty("user_password")
        String password

){}