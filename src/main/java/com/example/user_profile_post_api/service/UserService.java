package com.example.user_profile_post_api.service;

import com.example.user_profile_post_api.dto.request.UserCreateRequestDto;
import com.example.user_profile_post_api.dto.response.UserResponseDto;
import com.example.user_profile_post_api.dto.update.UserUpdateRequestDto;
import com.example.user_profile_post_api.entity.User;
import com.example.user_profile_post_api.mapper.UserMapper;
import com.example.user_profile_post_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /*
    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }*/


    //create user
    public UserResponseDto createUser(UserCreateRequestDto dto) {
        User user = userMapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }


    //get by id
    public UserResponseDto getUserById(Long userId) {
      /*  Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new EntityNotFoundException("User not found");
        }*/

        User user = userRepository.findById(userId).
                orElseThrow(() -> new NullPointerException("User not found of id" + userId));
        return userMapper.toResponse(user);

    }

    //get all
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userMapper.toResponse(user)).toList();
        // users.stream().map(userMapper::toResponse).toList();
    }

    //update
    public UserResponseDto updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.
                findById(userId).orElseThrow(() -> new RuntimeException("User not existt"));

        userMapper.updateEntity(userUpdateRequestDto, user);
        User updateUser = userRepository.save(user);
        return userMapper.toResponse(updateUser);

    }

    //delete user
    public void deleteUser(Long userId)
    {
        if(!userRepository.existsById(userId))
        {
            throw new EntityNotFoundException("Not Found");
        }
        else
        {
            userRepository.deleteById(userId);
        }

    }
}
