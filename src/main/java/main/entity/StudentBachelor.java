package main.entity;

import lombok.Data;

@Data
public class StudentBachelor extends Student {
    private String studentIdentifyCard;

    public StudentBachelor() {
    }

    public StudentBachelor(Integer id, String type, String name, String surname, String email, Integer course, String studentIdentifyCard) {
        super(id, type, name, surname, email, course);
        this.studentIdentifyCard = studentIdentifyCard;
    }

    @Override
    public void show() {
        System.out.println("----------------------------");
        System.out.println("Information about student");
        System.out.println("\tCurrent student is bachelor");
        System.out.println("\tname: " + getName());
        System.out.println("\tsurname: " + getSurname());
        System.out.println("\temail: " + getEmail());
        System.out.println("\tcourse: " + getCourse());
        System.out.println("\tstudentIdentifyCard: " + getStudentIdentifyCard());
    }
}
