package main.entity;

import lombok.Data;

@Data
public abstract class Student {
    private Integer id;
    private String type;
    private String name;
    private String surname;
    private String email;
    private Integer course;

    public Student() {
    }

    public Student(Integer id, String type, String name, String surname, String email, Integer course) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.course = course;
    }

    abstract public void show();
}
