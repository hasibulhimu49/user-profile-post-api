package com.example.user_profile_post_api.mapper;

import com.example.user_profile_post_api.dto.request.PostCreateRequestDto;
import com.example.user_profile_post_api.dto.response.PostResponseDto;
import com.example.user_profile_post_api.dto.update.PostUpdateRequestDto;
import com.example.user_profile_post_api.model.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    //dto to Entity
    public Post toEntity(PostCreateRequestDto requestDto)
    {
        if(requestDto==null)
        {
            return null;
        }

        Post post=new Post();
        post.setTitle(requestDto.title());
        post.setContent(requestDto.content());
        return post;
    }

    //entity to response(dto)
    public PostResponseDto toResponse(Post post)
    {
        if(post==null)
        {
            return null;
        }

        PostResponseDto responseDto=new PostResponseDto();
        responseDto.setPostId(post.getPostId());
        responseDto.setTitle(post.getTitle());
        responseDto.setContent(post.getContent());

        return responseDto;
    }

    //update entity from update dto

    public void updateEntity(PostUpdateRequestDto updateRequestDto,Post post)
    {
        if(updateRequestDto ==null || post ==null)
        {
            return;
        }

        if(updateRequestDto.getTitle()!=null)
        {
            post.setTitle(updateRequestDto.getTitle());
        }

        if(updateRequestDto.getContent()!=null)
        {
            post.setContent(updateRequestDto.getContent());
        }
    }
}
