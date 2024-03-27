package com.example.manageLibrary.DTO;

public class SearchDTO {
    private String input ;

    public SearchDTO(String input) {
        this.input = input;
    }

    public SearchDTO() {
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
