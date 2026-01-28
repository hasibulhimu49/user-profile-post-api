package com.example.user_profile_post_api.controller;

import com.example.user_profile_post_api.dto.request.UserCreateRequestDto;
import com.example.user_profile_post_api.dto.response.UserResponseDto;
import com.example.user_profile_post_api.dto.update.UserUpdateRequestDto;
import com.example.user_profile_post_api.model.enums.Gender;
import com.example.user_profile_post_api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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



    @GetMapping("all")
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






    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> getUserByUserName(@RequestParam String username){

        return ResponseEntity.ok(userService.getUserByUserName(username));

    }



    //Practice----Paging,Sorting,Projection,Specification,Auditing
    @GetMapping()
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(Pageable pageable) // spring automatically do------ Pageable pageable= PageRequest.of(0,5, Sort.by("firstName").ascending());
    {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }



}
