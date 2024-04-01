package com.example.manageLibrary.Specification;

import com.example.manageLibrary.Entities.Authors;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {
    public static Specification<Authors>searchAuthorByName(String name){
        return (Root<Authors>root , CriteriaQuery<?>query, CriteriaBuilder criteriaBuilder)->{
            Predicate predicate = criteriaBuilder.conjunction();
            if(name!=null&&!name.isEmpty()){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%"+name+"%"));
            }
            return predicate;
        };
    }
}
