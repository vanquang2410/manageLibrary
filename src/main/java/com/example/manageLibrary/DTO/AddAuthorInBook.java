package com.example.manageLibrary.DTO;

import com.example.manageLibrary.Entities.Authors;

import java.util.List;

public class AddAuthorInBook {
    private List<Long> listAuthorId;

    private List<Authors> newAuthors ;

    public AddAuthorInBook(List<Long> listAuthorId, List<Authors> newAuthors) {
        this.listAuthorId = listAuthorId;
        this.newAuthors = newAuthors;
    }

    public AddAuthorInBook() {
    }

    public List<Long> getListAuthorId() {
        return listAuthorId;
    }

    public void setListAuthorId(List<Long> listAuthorId) {
        this.listAuthorId = listAuthorId;
    }

    public List<Authors> getNewAuthors() {
        return newAuthors;
    }

    public void setNewAuthors(List<Authors> newAuthors) {
        this.newAuthors = newAuthors;
    }
}
