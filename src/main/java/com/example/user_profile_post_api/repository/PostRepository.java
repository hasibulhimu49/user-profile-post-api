package com.example.user_profile_post_api.repository;

import com.example.user_profile_post_api.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    //Query Creation (Derived Methods)
    List<Post> findByTitle(String title);





    /*
    //JPQL

    // old way before java 15

    @Query("SELECT p " +
       "FROM Post p " +
       "JOIN p.profile pr " +
       "JOIN pr.user u " +
       "WHERE u.username = :username")




    //""" is a Java Text Block (introduced in Java 15).
    @Query("""
    SELECT p 
    FROM Post p 
    JOIN p.profile pr 
    JOIN pr.user u 
    WHERE u.userName = :username
""")
    List<Post> findPostsByUsername(@Param("username") String username);

     */







    /*


    //Native Query-Use only when JPQL is not enough
    @Query(
            value = "SELECT * FROM post_table WHERE title LIKE %:keyword%",
            nativeQuery = true
    )
    List<Post> searchPostByTitle(@Param("keyword") String keyword);

     */

}
