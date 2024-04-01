package com.example.manageLibrary.Specification;


import com.example.manageLibrary.DTO.SearchBookDTO;
import com.example.manageLibrary.Entities.Authors;
import com.example.manageLibrary.Entities.Book;
import com.example.manageLibrary.Entities.Libraries;
import jakarta.persistence.criteria.*;
import org.springframework.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BookSpecifications {


    public static Specification<Book>   searchBookByInput(SearchBookDTO searchBookDTO) {
        return (Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (searchBookDTO.getCode() != null && !searchBookDTO.getCode().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("code"),"%"+ searchBookDTO.getCode()+"%"));
            }

            if (searchBookDTO.getName() != null && !searchBookDTO.getName().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%"+searchBookDTO.getName() +"%"));
            }

            if (searchBookDTO.getCategory() != null && !searchBookDTO.getCategory().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("category"), "%"+searchBookDTO.getCategory()+"%"));
            }

            if(searchBookDTO.getAuthor()!=null&& !searchBookDTO.getAuthor().isEmpty()){
                Join<Book, Authors> authorJoin = root.join("authors");
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(authorJoin.get("name"), "%" + searchBookDTO.getAuthor() + "%"));
            }
            if(searchBookDTO.getLibrary()!=null &&!searchBookDTO.getLibrary().isEmpty()){
                Join<Book,Libraries> librariesJoin =root.join("libraries");
                predicate= criteriaBuilder.and(predicate,criteriaBuilder.like(librariesJoin.get("name"),"%"+searchBookDTO.getLibrary()+"%"));
            }


            return predicate;
        };
    }

}
