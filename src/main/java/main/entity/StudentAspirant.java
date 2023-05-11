package main.entity;

import lombok.Data;

@Data
public class StudentAspirant extends Student {

    private Integer diplomaID;

    public StudentAspirant() {
    }

    public StudentAspirant(Integer id, String type, String name, String surname, String email, Integer course, Integer diplomaID) {
        super(id, type, name, surname, email, course);
        this.diplomaID = diplomaID;
    }

    @Override
    public void show() {
        System.out.println("----------------------------");
        System.out.println("Information about student");
        System.out.println("\tCurrent student is aspirant");
        System.out.println("\tname: " + getName());
        System.out.println("\tsurname: " + getSurname());
        System.out.println("\temail: " + getEmail());
        System.out.println("\tcourse: " + getCourse());
        System.out.println("\tDiplomaID: " + getDiplomaID());
    }
}