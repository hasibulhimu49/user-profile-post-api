package com.example.user_profile_post_api.repository;

import com.example.user_profile_post_api.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldExistByUsername()
    {

        User user=new User();
        user.setUsername("Hasibul");
        user.setEmail("hasibul@gmail.com");
        user.setPassword("123456");
        userRepository.save(user);


        // When
        Optional<User> exists=userRepository.findByUsername("Hasibul");

        //Then
        assertThat(exists).isPresent();
        assertThat(exists.get().getEmail()).isEqualTo("hasibul@gmail.com");

    }
}
