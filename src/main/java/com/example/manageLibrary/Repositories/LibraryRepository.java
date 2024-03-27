package com.example.manageLibrary.Repositories;

import com.example.manageLibrary.Entities.Authors;
import com.example.manageLibrary.Entities.Book;
import com.example.manageLibrary.Entities.Libraries;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Libraries,Long> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "books","books.authors","books.libraries","books.image",
            }
    )
    List<Libraries>findAll();
}
