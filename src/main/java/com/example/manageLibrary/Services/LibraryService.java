package com.example.manageLibrary.Services;

import com.example.manageLibrary.DTO.AddLibrabyDTO;
import com.example.manageLibrary.DTO.BookDTO;
import com.example.manageLibrary.DTO.SearchLibrary;
import com.example.manageLibrary.DTO.UpdateLibraryDTO;
import com.example.manageLibrary.Entities.Authors;
import com.example.manageLibrary.Entities.Book;
import com.example.manageLibrary.Entities.Libraries;
import com.example.manageLibrary.Repositories.LibraryRepository;
import com.example.manageLibrary.Response.LibraryResponse;
import com.example.manageLibrary.Specification.AuthorSpecification;
import com.example.manageLibrary.Specification.LibrarySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookService bookService;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public LibraryService() {
    }

    public Optional<Libraries> findById (Long id){
        return libraryRepository.findById(id);
    }

    public Libraries convertLibraryDTOToLibrary(AddLibrabyDTO addLibrabyDTO){
        Libraries libraries= new Libraries();
        libraries.setName(addLibrabyDTO.getLibrabyName());
        libraries.setLocation(addLibrabyDTO.getLocation());
        return libraries;
    }
    public  List<Libraries>searchLibraries(SearchLibrary searchLibrary){
        Specification<Libraries> spec = LibrarySpecification.searchLibraryByName(searchLibrary);
        return libraryRepository.findAll(spec);
    }

    public Libraries addLibrary(Libraries libraries, List<Long> listIdBookDTO){
        libraries.setBooks(bookService.getBookByListId(listIdBookDTO));
        return libraryRepository.save(libraries);
    }

    public Libraries editLibrary(UpdateLibraryDTO updateLibraryDTO,Long id){
        Optional<Libraries> librariesById = libraryRepository.findById(id);
        if(librariesById.isEmpty()){
            return null ;
        }
        else{
            Libraries editLibrary = librariesById.get();
            editLibrary.setBooks(bookService.getBookByListId(updateLibraryDTO.getIdBook()));
            editLibrary.setName(updateLibraryDTO.getLibrabyName());
            editLibrary.setLocation(updateLibraryDTO.getLocation());
            return libraryRepository.save(editLibrary);
        }
    }

    public  Libraries deleteLibrary (Long id){
        Optional<Libraries> librariesById = libraryRepository.findById(id);
        if(librariesById.isEmpty()){
            return null ;
        }
        else{
           libraryRepository.deleteById(id);
            return librariesById.get();
        }
    }
    public Libraries addBookInLibrary (AddLibrabyDTO addLibrabyDTO , Long id){
        Optional<Libraries> librariesById = libraryRepository.findById(id);

        if (librariesById.isEmpty()){
            return null ;
        }
        else {
            Libraries editLibrary = librariesById.get();
            List<Long>idBook ;
            idBook=addLibrabyDTO.getIdBook();
            idBook.addAll(addLibrabyDTO.getIdBook());
            editLibrary.setBooks(bookService.getBookByListId(idBook));
            return libraryRepository.save(editLibrary);
        }
    }
   public LibraryResponse convertLibraryToLibraryResponse(Libraries libraries){
        LibraryResponse libraryResponse= new LibraryResponse();
        libraryResponse.setId(libraries.getId());
        libraryResponse.setLocation(libraries.getLocation());
        libraryResponse.setName(libraries.getName());
        return  libraryResponse;
   }

}
