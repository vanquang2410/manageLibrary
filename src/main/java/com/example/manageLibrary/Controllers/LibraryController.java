package com.example.manageLibrary.Controllers;

import com.example.manageLibrary.DTO.AddLibrabyDTO;
import com.example.manageLibrary.DTO.BookDTO;
import com.example.manageLibrary.DTO.UpdateLibraryDTO;
import com.example.manageLibrary.Entities.Book;
import com.example.manageLibrary.Entities.Libraries;
import com.example.manageLibrary.Repositories.LibraryRepository;
import com.example.manageLibrary.Services.LibraryService;
import com.example.manageLibrary.Services.RespondObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("ok","add library sucessfully",libraryRepository.findAll())
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
    
}
