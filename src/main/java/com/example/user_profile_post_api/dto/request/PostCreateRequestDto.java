package com.example.user_profile_post_api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostCreateRequestDto(
        @JsonProperty("post_title")
        String title,

        @JsonProperty("post_content")
        String content

){}
