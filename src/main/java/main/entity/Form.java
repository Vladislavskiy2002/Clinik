package main.entity;

import lombok.Data;

@Data
public class Form {
    private String type;
    private String name;
    private String surname;
    private String email;
    private Integer course;
    private Integer diplomaID;
    private String studentIdentifyCard;

    public Form(String type, String name, String surname, String email, Integer course, Integer diplomaID, String studentIdentifyCard) {
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.course = course;
        this.diplomaID = diplomaID;
        this.studentIdentifyCard = studentIdentifyCard;
    }

    public Form(String name, String surname, Integer course) {
        this.name = name;
        this.surname = surname;
        this.course = course;
    }
}
