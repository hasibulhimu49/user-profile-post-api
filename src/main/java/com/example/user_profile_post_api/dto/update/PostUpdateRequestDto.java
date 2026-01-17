package com.example.user_profile_post_api.dto.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequestDto {


    @JsonProperty("post_title")
    String title;

    @JsonProperty("post_content")
    String content;
}
