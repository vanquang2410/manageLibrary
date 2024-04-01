package com.example.manageLibrary.Controllers;

import com.example.manageLibrary.DTO.*;
import com.example.manageLibrary.Entities.Book;
import com.example.manageLibrary.Entities.Libraries;
import com.example.manageLibrary.Repositories.BookRepository;
import com.example.manageLibrary.Services.BookService;
import com.example.manageLibrary.Services.LibraryService;
import com.example.manageLibrary.Services.RespondObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private LibraryService libraryService;


    @PostMapping("/search")
    public ResponseEntity<RespondObject>searchBook (@RequestBody SearchBookDTO searchBookDTO){
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","search",bookService.searchBooks(searchBookDTO))
        );
    }

    @PostMapping("")
    public ResponseEntity<RespondObject>addBook (@RequestBody BookDTO bookDTO){
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","add book sucessfully",bookService.insertBook(bookDTO))
        ) ;

    }

    @PutMapping("/{id}")
    public  ResponseEntity<RespondObject>updateBook(@RequestBody BookDTO bookDTO,@PathVariable Long id){
        Book updateBook = bookService.updateBook(bookDTO,id);
        if (updateBook==null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject( "failed","dont find book",null)
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject( "ok","update book successfully",bookService.updateBook(bookDTO,id))
            );
        }

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<RespondObject>deleteBook(@PathVariable Long id){
        Book deleteBook = bookService.deleteBook(id);
        if(deleteBook==null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject( "failed","dont find book",null)
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject( "OK","delete sucessfully",deleteBook)
            );
        }
    }

    @GetMapping("")
    public ResponseEntity<RespondObject>getBooks(@RequestParam int page){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable= PageRequest.of(page-1,10,sort);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","get book  sucessfull",bookRepository.findAll(pageable))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespondObject>getBookById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","get book  sucessfull",bookRepository.findById(id) )
        );
    }

}
