package com.example.library.models;


import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name;
    private int age;
    private String email;
    private long id;
    public Person(){

    }

    public Person(String name, int age, String email, long id) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.id = id;
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
