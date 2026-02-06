package com.example.user_profile_post_api.controller;

import com.example.user_profile_post_api.dto.request.UserCreateRequestDto;
import com.example.user_profile_post_api.dto.response.UserResponseDto;
import com.example.user_profile_post_api.dto.update.UserUpdateRequestDto;
import com.example.user_profile_post_api.exception.MyCustomException;
import com.example.user_profile_post_api.model.enums.Gender;
import com.example.user_profile_post_api.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.BadAttributeValueExpException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    //private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> userCreate(@Valid @RequestBody UserCreateRequestDto dto) {

        log.info("User request DTO: {}",dto);

        /*
        if (true) {
            throw new RuntimeException("User Cannot be created");
        }
        */


        /*
        if (true) {
            throw new MyCustomException("User Cannot be created");
        }
        */

        if(Character.isDigit(dto.username().charAt(0)))
        {
            throw new IllegalArgumentException("Username cannot start with a number" +dto.username());
        }

        if(Character.isLetter(dto.email().charAt(0)))
        {
            throw new IllegalArgumentException(("Email cannot be start with a letter" +dto.email()));
        }



        UserResponseDto responseDto = userService.createUser(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    //Local Exception handler
   /* @ExceptionHandler(value = {MyCustomException.class, EntityNotFoundException.class})
    public Object localExceptionHandler(RuntimeException e) {
        return e.getMessage();

    }*/


    @GetMapping("all")
    public ResponseEntity<List<UserResponseDto>> getAllBooks() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }



    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDto requestDto) {
        return ResponseEntity.ok(userService.updateUser(userId, requestDto));

    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> getUserByUserName(@RequestParam String username) {

        return ResponseEntity.ok(userService.getUserByUserName(username));

    }


    //Practice----Paging,Sorting,Projection,Specification,Auditing
    @GetMapping()
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(Pageable pageable) // spring automatically do------ Pageable pageable= PageRequest.of(0,5, Sort.by("firstName").ascending());
    {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }


}
