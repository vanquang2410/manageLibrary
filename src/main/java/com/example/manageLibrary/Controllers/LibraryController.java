package com.example.manageLibrary.Controllers;

import com.example.manageLibrary.DTO.AddLibrabyDTO;
import com.example.manageLibrary.DTO.BookDTO;
import com.example.manageLibrary.DTO.SearchLibrary;
import com.example.manageLibrary.DTO.UpdateLibraryDTO;
import com.example.manageLibrary.Entities.Book;
import com.example.manageLibrary.Entities.Libraries;
import com.example.manageLibrary.Repositories.LibraryRepository;
import com.example.manageLibrary.Response.LibraryResponse;
import com.example.manageLibrary.Services.LibraryService;
import com.example.manageLibrary.Services.RespondObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController
{
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryService libraryService;

        @PostMapping("")
        ResponseEntity<RespondObject>addLibrary (@RequestBody AddLibrabyDTO addLibrabyDTO)  {
                Libraries libraryCovert = libraryService.convertLibraryDTOToLibrary(addLibrabyDTO);
                Libraries libraryNew =libraryService.addLibrary(libraryCovert,addLibrabyDTO.getIdBook());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("ok","add library sucessfully",libraryNew)
                );
        }

    @PostMapping("/search")
    public ResponseEntity<RespondObject>searchLibrary (@RequestBody SearchLibrary searchLibrary){
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok","search",libraryService.searchLibraries(searchLibrary))
        );
    }

        @GetMapping("")
        ResponseEntity<RespondObject>getAllLibraries (@RequestParam int page)  {
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            Pageable pageable = PageRequest.of(page-1,10,sort);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("ok","add library sucessfully",libraryRepository.findAll(pageable))
            );
        }

        @PutMapping("/{id}")
        ResponseEntity<RespondObject>editLibrary (@RequestBody UpdateLibraryDTO updateLibraryDTO, @PathVariable Long id)  {
            Libraries editLibrary = libraryService.editLibrary(updateLibraryDTO,id);
            if (editLibrary==null){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("failed","dont find library",null)
                );
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK","edit library sucessfully",editLibrary)
                );
            }
        }

        @DeleteMapping("/{id}")
        ResponseEntity<RespondObject>deleteLibrary(@PathVariable Long id){
            Libraries deleteLibrary = libraryService.deleteLibrary(id);
            if (deleteLibrary==null){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("failed","dont find library",null)
                );
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK","delete library sucessfully",deleteLibrary)
                );
            }
        }

    @PostMapping("/{id}/books")
    public ResponseEntity<RespondObject>addBookInLibrary(@RequestBody AddLibrabyDTO addLibrabyDTO, @PathVariable Long id ){
            Libraries addBook = libraryService.addBookInLibrary(addLibrabyDTO,id);
            if(addBook==null){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("failed","dont find id","")
                );
            }
            else {
                return  ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK","add book in library successfully",addBook)
                );
            }

    }

    @GetMapping("/{id}")
    public ResponseEntity<RespondObject>getLibraryById(@PathVariable Long id ){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK","get  library successfully",libraryService.convertLibraryToLibraryResponse(libraryRepository.findById(id).get()))
            );
    }
    @GetMapping("/{id}/books")
    public ResponseEntity<RespondObject>getBookByIdLibrary(@PathVariable Long id ,@RequestParam int page){
        Optional<Libraries> librariesOptional =  libraryRepository.findById(id);
        Libraries librariesById=librariesOptional.get();
        List<?> booksPage=new ArrayList<>(librariesById.getBooks());
        return  ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("OK","get book in library successfully",booksPage.subList((page-1)*10,page*10))
        );
    }

}
