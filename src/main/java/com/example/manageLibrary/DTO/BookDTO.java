package com.example.manageLibrary.DTO;

import com.example.manageLibrary.Entities.Authors;
import com.example.manageLibrary.Entities.Book;
import com.example.manageLibrary.Entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public class BookDTO {
    private String code;

    private String name;

    private String category;

    private Set<Image> Image;

    private  List<Long>listIdAuthor;

    private  List<Authors>listNewAuthor ;

    public List<Long> getListIdAuthor() {
        return listIdAuthor;
    }

    public void setListIdAuthor(List<Long> listIdAuthor) {
        this.listIdAuthor = listIdAuthor;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<com.example.manageLibrary.Entities.Image> getImage() {
        return Image;
    }

    public void setImage(Set<Image> image) {
        Image = image;
    }

    public List<Authors> getListNewAuthor() {
        return listNewAuthor;
    }

    public void setListNewAuthor(List<Authors> listNewAuthor) {
        this.listNewAuthor = listNewAuthor;
    }
}
