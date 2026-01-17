package com.example.user_profile_post_api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ProfileCreateRequestDto(
        @NotNull(message = "Name can not be null")
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,


        @JsonProperty("bio")
        String bio
){}
