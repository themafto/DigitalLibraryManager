package com.example.library.models;


import javax.validation.constraints.*;

public class Person {
    @NotEmpty(message = "The name must not be blank")
    @Size(min = 2, max = 100, message = "The name should be between 2 and 100 characters long")
    private String name;
    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 150, message = "The age must be less than 150")
    private int age;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Incorrect email format")
    private String email;
    private long id;
    public Person(){

    }

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
