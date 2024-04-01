package com.example.manageLibrary.Controllers;

import com.example.manageLibrary.DTO.AddLibrabyDTO;
import com.example.manageLibrary.DTO.SearchAuthorsDTO;
import com.example.manageLibrary.DTO.SearchBookDTO;
import com.example.manageLibrary.Entities.Authors;
import com.example.manageLibrary.Entities.Libraries;
import com.example.manageLibrary.Repositories.AuthorRepository;
import com.example.manageLibrary.Services.AuthorService;
import com.example.manageLibrary.Services.RespondObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;


    @PostMapping("")
    ResponseEntity<RespondObject>addAuthor (@RequestBody Authors authors)  {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","add author sucessfully",authorRepository.save(authors))
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<RespondObject>getAuthorById (@PathVariable Long id)  {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","add author sucessfully",authorRepository.findById(id))
        );
    }
    @PostMapping("/search")
    public ResponseEntity<RespondObject>searchAuthor (@RequestBody SearchAuthorsDTO searchAuthorsDTO){
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","search",authorService.searchAuthors(searchAuthorsDTO.getName()))
        );
    }

    @GetMapping("")
    ResponseEntity<RespondObject>getAuthor (@RequestParam int page )  {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page-1,10,sort);

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","get author sucessfully",authorRepository.findAll(pageable))
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<RespondObject>deleteAuThorById (@PathVariable Long id)  {
        authorRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","delete author sucessfully","")
        );
    }




    @PutMapping("/{id}")
    ResponseEntity<RespondObject>updateAuthorById(@PathVariable Long id ,@RequestBody Authors authors){
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","update author sucessfully",authorService.updateAuthorById(authors,id))
        );
    }
    @GetMapping("/export")
    ResponseEntity<RespondObject>export(){

        List<Authors>listAuthor= new ArrayList<>();
        Authors authors = new Authors();
        for (Long i = 1L; i<=20L ; i++){
            authors.setId(i);
            authors.setName("tac gia "+i);
            authors.setYear(1990);
            listAuthor.add(authors);
        }
        authorRepository.saveAll(listAuthor);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","update author sucessfully","")
        );
    }

}
