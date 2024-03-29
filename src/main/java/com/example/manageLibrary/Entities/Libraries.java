package com.example.manageLibrary.Entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Libraries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id ;

    private String name ;
    private String location ;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "library_books",
            joinColumns = @JoinColumn(name = "library_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books;



    public Libraries(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Libraries() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}


