package com.example.user_profile_post_api.repository;

import com.example.user_profile_post_api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
