package com.example.user_profile_post_api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_table")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;


    @Column(name ="username" , nullable = false, unique = true)
    String username;

    @Column(nullable = false,unique = true)
    String email;

    @Column
    String password;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;


}
