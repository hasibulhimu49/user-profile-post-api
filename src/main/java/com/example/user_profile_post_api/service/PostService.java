package com.example.user_profile_post_api.service;

import com.example.user_profile_post_api.dto.request.PostCreateRequestDto;
import com.example.user_profile_post_api.dto.response.PostResponseDto;
import com.example.user_profile_post_api.dto.update.PostUpdateRequestDto;
import com.example.user_profile_post_api.entity.Post;
import com.example.user_profile_post_api.entity.Profile;
import com.example.user_profile_post_api.mapper.PostMapper;
import com.example.user_profile_post_api.repository.PostRepository;
import com.example.user_profile_post_api.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private  final PostRepository postRepository;
    private final PostMapper postMapper;
    private final ProfileRepository profileRepository;

    //create post
    public PostResponseDto createPost(Long profileId,PostCreateRequestDto requestDto)
    {
        Profile profile=profileRepository.findById(profileId).orElseThrow(()->new EntityNotFoundException("Not found"));

        Post post=postMapper.toEntity(requestDto);
        post.setProfile(profile);// relationship handle here
        Post savedPost= postRepository.save(post);
        return postMapper.toResponse(savedPost);
    }

    //get post by id
    public PostResponseDto getPostById(Long postId)
    {
        Post post=postRepository.findById(postId).orElseThrow(()->new EntityNotFoundException("Not found"));
        return postMapper.toResponse(post);
    }

    //get all post
    public List<PostResponseDto> getAllPost()
    {
        List<Post> posts=postRepository.findAll();
        return posts.stream().map(post->postMapper.toResponse(post)).toList();
    }


    //update post
    public PostResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto)
    {
        Post post=postRepository.findById(postId).
                orElseThrow(()->new EntityNotFoundException("Not found"));
        postMapper.updateEntity(requestDto,post);
        Post updatePost=postRepository.save(post);
        return postMapper.toResponse(updatePost);

    }

    //delete post
    public void deletePost(Long postId)
    {
        if(!postRepository.existsById(postId))
        {
            throw new EntityNotFoundException("Not found");
        }
        else {
            postRepository.deleteById(postId);
        }
    }
}
