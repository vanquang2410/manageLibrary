package com.example.manageLibrary.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateLibraryDTO {
    private String librabyName;
    private String location;

    private List<Long> idBook;

}
