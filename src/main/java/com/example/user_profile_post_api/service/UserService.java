package com.example.user_profile_post_api.service;

import com.example.user_profile_post_api.dto.request.UserCreateRequestDto;
import com.example.user_profile_post_api.dto.response.UserResponseDto;
import com.example.user_profile_post_api.dto.update.UserUpdateRequestDto;
import com.example.user_profile_post_api.model.entity.User;
import com.example.user_profile_post_api.mapper.UserMapper;
import com.example.user_profile_post_api.model.enums.Gender;
import com.example.user_profile_post_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
                orElseThrow(() -> new EntityNotFoundException("User not found of id" + userId));
        return userMapper.toResponse(user);

    }

    //get all
    public List<UserResponseDto> getAllUsers() {
        // Pageable pageable= PageRequest.of(1,5, Sort.by("email").ascending());
        // Page<User> users=userRepository.findAll(pageable);


        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userMapper.toResponse(user)).toList();
            // users.stream().map(userMapper::toResponse).toList();
    }

    //update
    public UserResponseDto updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.
                findById(userId).orElseThrow(() -> new EntityNotFoundException("User not existt"));

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





    public List<UserResponseDto>  getUserByUserName(String username){
        Optional<User> users = userRepository.findByUsername(username);
        return users.stream()
                .map(user -> userMapper.toResponse(user))
                .toList();
    }



    //Practice----Paging,Sorting,Projection,Specification,Auditing

    public Page<UserResponseDto> getAllUsers(Pageable pageable)
    {
        return userRepository.findAll(pageable).map(userMapper::toResponse); //(u)->userMapper.toResponse(u)
    }
}
