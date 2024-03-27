package com.example.manageLibrary.Repositories;

import com.example.manageLibrary.Entities.Image;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ImageRepository extends JpaRepository<Image,Long> {


    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "books",
            }
    )
    <S extends Image> List<S> saveAll(Iterable<S> entities);
}
