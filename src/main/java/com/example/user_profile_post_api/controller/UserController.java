package com.example.user_profile_post_api.controller;

import com.example.user_profile_post_api.dto.request.UserCreateRequestDto;
import com.example.user_profile_post_api.dto.response.UserResponseDto;
import com.example.user_profile_post_api.dto.update.UserUpdateRequestDto;
import com.example.user_profile_post_api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> userCreate(@Valid @RequestBody UserCreateRequestDto dto)
    {
       UserResponseDto responseDto=userService.createUser(dto);
       return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllBooks()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }




    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById( @PathVariable Long userId)
    {
        return ResponseEntity.ok(userService.getUserById(userId));
    }



    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId,@RequestBody UserUpdateRequestDto requestDto)
    {
        return ResponseEntity.ok(userService.updateUser(userId,requestDto));

    }



    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId)
    {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }



}
