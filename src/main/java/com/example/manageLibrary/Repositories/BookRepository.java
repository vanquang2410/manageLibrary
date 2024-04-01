package com.example.manageLibrary.Repositories;

import com.example.manageLibrary.Entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> , JpaSpecificationExecutor<Book> {
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "authors","libraries","image"
            }
    )
    List<Book> findAll(Specification<Book> spec);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "authors","libraries","image"
            }
    )
    List<Book>findAll();

    @Override
    List<Book> findAllById(Iterable<Long> longs);
}
