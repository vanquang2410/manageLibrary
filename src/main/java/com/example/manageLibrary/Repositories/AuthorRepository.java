package com.example.manageLibrary.Repositories;

import com.example.manageLibrary.Entities.Authors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Authors,Long>, JpaSpecificationExecutor<Authors> {
    void deleteById (Long id);

    List<Authors>findAll(Specification<Authors>spec);

}
