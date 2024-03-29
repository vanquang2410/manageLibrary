package com.example.manageLibrary.Controllers;

import com.example.manageLibrary.DTO.AddLibrabyDTO;
import com.example.manageLibrary.DTO.BookDTO;
import com.example.manageLibrary.DTO.UpdateLibraryDTO;
import com.example.manageLibrary.Entities.Book;
import com.example.manageLibrary.Entities.Libraries;
import com.example.manageLibrary.Repositories.LibraryRepository;
import com.example.manageLibrary.Response.LibraryResponse;
import com.example.manageLibrary.Services.LibraryService;
import com.example.manageLibrary.Services.RespondObject;
import org.springframework.beans.factory.annotation.Autowired;
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

        @GetMapping("")
        ResponseEntity<RespondObject>getAllLibraries ()  {

            List<Libraries>findAllLibrary=libraryRepository.findAll();
            List<LibraryResponse>convert=new ArrayList<>();
            for (int i =0 ; i <findAllLibrary.size(); i++){
                Libraries lib= findAllLibrary.get(i);
                LibraryResponse newlibraryRespond =libraryService.convertLibraryToLibraryResponse(lib);
                convert.add(newlibraryRespond);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("ok","add library sucessfully",convert)
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
    public ResponseEntity<RespondObject>getBookByIdLibrary(@PathVariable Long id ){

        return  ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("OK","get book in library successfully",libraryRepository.findById(id).get().getBooks())
        );
    }

}
