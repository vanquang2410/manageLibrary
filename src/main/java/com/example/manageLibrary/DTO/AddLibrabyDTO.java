package com.example.manageLibrary.DTO;

import com.example.manageLibrary.Entities.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddLibrabyDTO {
    private String librabyName;
    private String location;

    private List<Long> idBook;




}
