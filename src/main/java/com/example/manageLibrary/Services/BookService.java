package com.example.manageLibrary.Services;

import com.example.manageLibrary.DTO.AddAuthorInBook;
import com.example.manageLibrary.DTO.BookDTO;
import com.example.manageLibrary.DTO.SearchBookDTO;
import com.example.manageLibrary.Entities.Authors;
import com.example.manageLibrary.Entities.Book;
import com.example.manageLibrary.Entities.Image;
import com.example.manageLibrary.Repositories.AuthorRepository;
import com.example.manageLibrary.Repositories.BookRepository;
import com.example.manageLibrary.Repositories.ImageRepository;
import com.example.manageLibrary.Specification.BookSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ImageRepository imageRepository;



    private  BookSpecifications bookSpecifications;

    @Autowired
    private AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookService() {
    }

    public Book addBookInLibrary(Book newBook){
        return bookRepository.save(newBook);
    }

//    public Book convertRequestParamToBook (AddBookInLibrary addBookInLibrary, Long libraryId) throws IOException {
//
//        Book newBook = new Book();
//        Set<Authors>listAuthorInBook=setAuthorWhenAddBook(addBookInLibrary.getListAuthorId(),addBookInLibrary.getNewAuthors());
//        newBook.setAuthors(listAuthorInBook);
//        newBook.setCode(addBookInLibrary.getCode());
//        newBook.setName(addBookInLibrary.getName());
//        newBook.setCategory(addBookInLibrary.getCategory());
//        newBook.setLibId(libraryId);
//        Book addBook = bookRepository.save(newBook);
//        Set<Image>listImageAfterCreate=addImageWhenAddBook(addBookInLibrary.getImage(),addBook);
//        addBook.setImage(listImageAfterCreate);
//        return bookRepository.save(addBook);
//    }

    public List<Book> getBook(){
        return bookRepository.findAll();
    }

    public  Set<Image>addImageWhenAddBook(Set<Image> imagesList, Book book){
        List<Image>newListImages=new ArrayList<>();

        Image image = new Image();
        for(Image pic:imagesList){
            image.setPath(pic.getPath());
            image.setBookId(book.getId());
            newListImages.add(image);
        }
        Set<Image>listImageAfterCreate=imageRepository.saveAll(newListImages).stream().collect(Collectors.toSet());
        return listImageAfterCreate;
    }

    public List<Book> saveAllBook (List<BookDTO> bookDTOS, Long id){
        List<Book> books = new ArrayList<>();
        Set<Authors>listAuthorInBook=new HashSet<>();
        for (BookDTO bookDTO : bookDTOS) {
            Book book = new Book();
            book.setCode(bookDTO.getCode());
            book.setName(bookDTO.getName());
            book.setCategory(bookDTO.getCategory());
            book.setLibId(id);
            book.setImage(bookDTO.getImage());
            books.add(book);
        }
        return bookRepository.saveAll(books);
    }

    public List<Book> searchBooks(SearchBookDTO searchBookDTO) {
        Specification<Book> spec = BookSpecifications.searchBookByInput(searchBookDTO);
        return bookRepository.findAll(spec);
    }

    public Book insertBook (BookDTO bookDTO){
        Book newBook = new Book();
        List<Authors>listAuthor = authorRepository.findAllById(bookDTO.getListIdAuthor());
        Set<Authors> listAuthorInBook= new HashSet<>(listAuthor);
        newBook.setAuthors(listAuthorInBook);
        newBook.setCode(bookDTO.getCode());
        newBook.setName(bookDTO.getName());
        newBook.setCategory(bookDTO.getCategory());
        Book addBook = bookRepository.save(newBook);
        Set<Image>listImageAfterCreate=addImageWhenAddBook(bookDTO.getImage(),addBook);
        addBook.setImage(listImageAfterCreate);
        return bookRepository.save(addBook);

    }
    public Book updateBook (BookDTO bookDTO,Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        Book updateBook = bookOptional.get();
        List<Authors>listAuthor = authorRepository.findAllById(bookDTO.getListIdAuthor());
        Set<Authors> listAuthorInBook= new HashSet<>(listAuthor);
        updateBook.setAuthors(listAuthorInBook);
        updateBook.setCode(bookDTO.getCode());
        updateBook.setName(bookDTO.getName());
        updateBook.setCategory(bookDTO.getCategory());
        Book addBook = bookRepository.save(updateBook);
        Set<Image>listImageAfterCreate=addImageWhenAddBook(bookDTO.getImage(),addBook);
        addBook.setImage(listImageAfterCreate);
        return bookRepository.save(addBook);
    }

    public  Book deleteBook (Long id){
        Optional<Book> book= bookRepository.findById(id);
        if(book.isEmpty()){
            return null;
        }
        else {
           bookRepository.deleteById(id);
           return book.get();
        }
    }
    private List<Authors> getAuthorsByIds(List<Long> authorIds) {
        return authorRepository.findAllById(authorIds);
    }

    public  Set<Book>getBookByListId(List<Long>listId){
        List<Book>getAllBookById= bookRepository.findAllById(listId);
        return new HashSet<>(getAllBookById);
    }

    public  Book addAuthorInBook(AddAuthorInBook addAuthorInBook,Book book){
        Set<Authors>listAuthorInBook=setAuthorWhenAddBook(addAuthorInBook.getListAuthorId());
        book.setAuthors(listAuthorInBook);
        return bookRepository.save(book);
    }
    public  Set<Authors>setAuthorWhenAddBook (   List<Long> listAuthorId ){
        //get list author by ids
        List <Authors> authorsByListIds=getAuthorsByIds(listAuthorId);
        // convert list to set
        Set<Authors> listAuthorInBook = new HashSet<>(authorsByListIds);
        return listAuthorInBook;
    }



}
