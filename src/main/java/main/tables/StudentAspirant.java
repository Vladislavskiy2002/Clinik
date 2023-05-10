package main.tables;

public class StudentAspirant extends Student {
    public StudentAspirant() {
    }

    public StudentAspirant(Integer id, String type, String name, String surname, String email, Integer course) {
        super(id, type, name, surname, email, course);
    }

    @Override
    public void show() {
        System.out.println("----------------------------");
        System.out.println("Information about student");
        System.out.println("Current student is aspirant");
        System.out.println("name: " + getName());
        System.out.println("surname: " + getSurname());
        System.out.println("email: " + getEmail());
        System.out.println("course: " + getCourse());
    }
}