package com.example.manageLibrary.Specification;

import com.example.manageLibrary.DTO.SearchLibrary;
import com.example.manageLibrary.Entities.Authors;
import com.example.manageLibrary.Entities.Libraries;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class LibrarySpecification {
    public static Specification<Libraries> searchLibraryByName(SearchLibrary searchLibrary){
        return (Root<Libraries> root , CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->{
            Predicate predicate = criteriaBuilder.conjunction();
            if(searchLibrary.getName()!=null&&!searchLibrary.getName().isEmpty()){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%"+searchLibrary.getName() +"%"));
            }
            if(searchLibrary.getLocation()!=null&&!searchLibrary.getLocation().isEmpty()){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("location"), "%"+searchLibrary.getName() +"%"));
            }
            return predicate;
        };
    }
}
