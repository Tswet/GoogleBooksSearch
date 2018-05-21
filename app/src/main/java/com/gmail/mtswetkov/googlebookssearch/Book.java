package com.gmail.mtswetkov.googlebookssearch;

import java.io.Serializable;

public class Book implements Serializable {
    String title;
    String authors;
    String description;
    String subtitle;


    public Book(String title, String authors, String description, String subtitle) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.subtitle = subtitle;
    }
    public Book(){

    }
}
