package com.example.user_profile_post_api.repository;

import com.example.user_profile_post_api.model.entity.Profile;
import com.example.user_profile_post_api.model.entity.User;
import com.example.user_profile_post_api.model.enums.Gender;
import com.example.user_profile_post_api.repository.projection.ProfileSummary;
import jakarta.persistence.Column;
import jakarta.persistence.criteria.Join;
import org.hibernate.sql.Update;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>,JpaSpecificationExecutor<Profile> {


    //Query Creation (Derived Methods)
    List<Profile> findByFirstNameAndGender(String firstname, Gender gender);


    //JPQL
    @Query("""
            select p from Profile p where p.user.username=:username
            /*select p from Profile p join p.user u where u.username=:username*/
            """)
    Optional<Profile> findByUsername(@Param("username") String username);

        /*

            | JPQL                  | Native SQL            |
            | --------------------- | --------------------- |
            | Uses **Entity names** | Uses **Table names**  |
            | Uses **field names**  | Uses **column names** |
            | Database-independent  | Database-specific     |
            | Object-oriented       | SQL-oriented          |

     */

    //Native query
    @Query(
            value = """
                    select * from profile_table where bio like concat('%', :keyword ,'%')
                    """, nativeQuery = true)
    List<Profile> searchProfileByBio(@Param("keyword") String keyword);






    //N+1 Solution (JOIN FETCH)
    @Query("""
                select distinct p 
                from Profile p 
                join fetch p.posts
            """)
    List<Profile> findAllProfile();


    //N+1 Solution (Using @EntityGraph)
    @EntityGraph(attributePaths = "posts")
    List<Profile> findAll();







    //Paging
    Page<Profile> findByFirstNameContaining(String keyword, Pageable pageable);


    //JPQL
    @Query("""
            select p from Profile p where p.firstName LIKE %:name%
            """)
    Page<Profile> searchJPQL(@Param("name") String firstname, Pageable pageable);


    //Native
    @Query(
            value = "SELECT * FROM profile_table WHERE first_name LIKE %:name%",
            nativeQuery = true
    )
    List<Profile> searchNative(@Param("name") String name);





    //Projection
    List<ProfileSummary> findByLastName(String lastname);


}
