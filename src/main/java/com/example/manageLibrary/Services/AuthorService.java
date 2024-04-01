package com.example.manageLibrary.Services;

import com.example.manageLibrary.DTO.SearchBookDTO;
import com.example.manageLibrary.Entities.Authors;
import com.example.manageLibrary.Entities.Book;
import com.example.manageLibrary.Repositories.AuthorRepository;
import com.example.manageLibrary.Specification.AuthorSpecification;
import com.example.manageLibrary.Specification.BookSpecifications;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@Getter
@Setter
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Authors updateAuthorById(Authors author,Long id ){
        Optional<Authors> oldAuthor = authorRepository.findById(id);
        Authors newAuthor = new Authors(oldAuthor.get().getId(), author.getName(),author.getYear());
        return authorRepository.save(newAuthor);
    }

    public List<Authors> searchAuthors(String name) {

        Specification<Authors> spec = AuthorSpecification.searchAuthorByName(name);
        return authorRepository.findAll(spec);
    }


}
