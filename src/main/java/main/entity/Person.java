package main.entity;

import lombok.Data;

@Data
public class Person {
    private Integer id;
    private String name;
    private String surName;
    private String email;

    public Person(String email) {
        this.email = email;
    }

    public Person(String name, String surName, String email) {
        this.name = name;
        this.surName = surName;
        this.email = email;
    }
}
