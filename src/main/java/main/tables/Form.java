package main.tables;

import lombok.Data;

@Data
public class Form {
    private String type;
    private String name;
    private String surname;
    private String email;
    private Integer course;

    public Form(String type, String name, String surname, String email, Integer course) {
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.course = course;
    }

    public Form(String name, String surname, Integer course) {
        this.name = name;
        this.surname = surname;
        this.course = course;
    }
}
