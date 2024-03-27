    package com.example.manageLibrary.Entities;

    import jakarta.persistence.*;

    import java.util.List;

    @Entity
    public class Authors {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private  Long id ;

        private  String name ;

        private  int year;


        public Authors() {
        }

        public Authors(Long id, String name, int year) {
            this.id = id;
            this.name = name;
            this.year = year;
        }

        @ManyToMany(mappedBy = "authors", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
        private List<Book> books ;


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

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }


    }
