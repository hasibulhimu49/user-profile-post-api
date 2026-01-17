package com.example.user_profile_post_api.repository;

import com.example.user_profile_post_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {
    List<User> readUsersByUserId(Long userId);
}
