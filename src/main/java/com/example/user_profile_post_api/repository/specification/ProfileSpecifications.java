package com.example.user_profile_post_api.repository.specification;

import com.example.user_profile_post_api.model.entity.Profile;
import com.example.user_profile_post_api.model.enums.Gender;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class ProfileSpecifications {


    //This is not professional way
    public static Specification<Profile> hasFirstName(String firstName)
    {
        return new Specification<Profile>()        //Anonymous class
        {
            @Override
            public Predicate toPredicate(Root<Profile> root, CriteriaQuery<?> query, CriteriaBuilder cb)
            {

                if(firstName==null || firstName.isEmpty())
                {
                    return null;
                }
                return cb.like(root.get("firstName"),"%"+firstName+"%");  //Equivalent SQL: WHERE first_name LIKE '%Hasib%'

            }

        };
    }


    //This is not professional way
    public static Specification<Profile> hasLastName(String lastName)
    {
        return new Specification<Profile>()
        {
            @Override
            public  Predicate toPredicate(Root<Profile> root, CriteriaQuery<?> query, CriteriaBuilder cb)
            {
               if(lastName==null||lastName.isEmpty())
               {
                   return null;
               }
               return cb.like(root.get("lastName"), "%"+lastName+"%");
            }
        };
    }


    //this is good way
    public static Specification<Profile> hasBio(String bio)
    {
        return (root, query, cb) ->{
            if(bio==null|| bio.isEmpty())
            {
                return null;
            }
            return cb.like(root.get("bio"),"%"+bio+"%");

        };

    }

    //this is best way
    public static Specification<Profile> hasGender(Gender gender)
    {
        return (root, query, cb) ->
            gender==null?null:cb.equal(root.get("gender"),gender);


    }


    public static Specification<Profile> hasUsername(String username)
    {
        return (root, query, criteriaBuilder) ->
                username==null?null:criteriaBuilder.equal(root.join("user").get("username"),username);
    }


}

