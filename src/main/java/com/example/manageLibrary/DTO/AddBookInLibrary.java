package com.example.manageLibrary.DTO;

import com.example.manageLibrary.Entities.Authors;
import com.example.manageLibrary.Entities.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddBookInLibrary {
    private String name ;
    private String code;
    private String category;
    private List<Image>Image;
    private List<Long> listAuthorId;
    private List<Authors> newAuthors ;
}
