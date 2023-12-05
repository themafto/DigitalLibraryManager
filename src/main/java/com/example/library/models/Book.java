package com.example.library.models;

import org.springframework.beans.factory.annotation.Autowired;

public class Book {
    private String name;
    private String author;
    private int year;
    private long id;

    public Book(){}

    public Book(String name, String author, int year, long id) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
