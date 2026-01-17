package com.example.user_profile_post_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "profile_table")
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long profileId;

    @Column(name = "first_name", length = 50)
    String firstName;

    @Column(name = "last_name", length = 100)
    String lastName;

    @Column(length = 200)
    String bio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;
}
