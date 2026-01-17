package com.example.user_profile_post_api.repository;

import com.example.user_profile_post_api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
