package com.example.user_profile_post_api.repository;

import com.example.user_profile_post_api.model.entity.Post;
import com.example.user_profile_post_api.model.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    //Query Creation (Derived Methods)
    Optional<User> findByUsername(String username);
    List<User> readByUserId(Long userId);



    /*

    //JPQL

    //Indexed Query Parameters
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT p FROM Post p WHERE p.title = ?1 AND p.content = ?2")
    List<Post> findByTitleAndContent(String title, String content);



    //Named Query Parameters (BEST PRACTICE)
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT p FROM Post p WHERE p.title = :title")
    List<Post> findByTitle(@Param("title") String title);



    //Collection Parameters in Queries--Example: Find posts by multiple profile IDs
    @Query("SELECT p FROM Post p WHERE p.profile.id IN :profileIds")
    List<Post> findByProfileIds(@Param("profileIds") List<Long> profileIds);



 */





/*
    //Native Query-Use only when JPQL is not enough
    @Query(value = "SELECT * FROM User_table WHERE email LIKE %:hasibul%", nativeQuery = true)
    List<Post> searchPostByTitle(@Param("hasibul") String email);
*/






    //Prevent N+1 problem
       //JOIN FETCH → Repository
    @Query("""
        SELECT u
        FROM User u
        LEFT JOIN FETCH u.profile
    """)
    List<User> findAllWithProfile();

    //EntityGraph → Repository
     @EntityGraph(attributePaths = {"profile"})
     List<User> findAll();


}
