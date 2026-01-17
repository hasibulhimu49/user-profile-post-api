package com.example.user_profile_post_api.controller;

import com.example.user_profile_post_api.dto.request.PostCreateRequestDto;
import com.example.user_profile_post_api.dto.response.PostResponseDto;
import com.example.user_profile_post_api.dto.update.PostUpdateRequestDto;
import com.example.user_profile_post_api.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    public final PostService postService;

    @PostMapping("/profile/{profileId}")
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostCreateRequestDto requestDto, @PathVariable Long profileId)
    {
        PostResponseDto response=postService.createPost(profileId,requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }



    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPost()
    {
        return ResponseEntity.ok(postService.getAllPost());
    }



    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId)
    {
        return ResponseEntity.ok(postService.getPostById(postId));
    }



    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto updateRequestDto)
    {
        return ResponseEntity.ok(postService.updatePost(postId,updateRequestDto));
    }



    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId)
    {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }


}
