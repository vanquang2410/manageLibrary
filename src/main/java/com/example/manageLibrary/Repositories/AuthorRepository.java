package com.example.manageLibrary.Repositories;

import com.example.manageLibrary.Entities.Authors;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Authors,Long> {
    void deleteById (Long id);
}
