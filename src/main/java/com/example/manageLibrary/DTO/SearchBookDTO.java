package com.example.manageLibrary.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SearchBookDTO {
    private  String code;
    private  String name;
    private  String category;
    private  String library;
    private  String author ;
}


