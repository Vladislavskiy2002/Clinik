package main.tables;

public class StudentBachelor extends Student {
    public StudentBachelor() {
    }

    public StudentBachelor(Integer id, String type, String name, String surname, String email, Integer course) {
        super(id, type, name, surname, email, course);
    }

    @Override
    public void show() {
        System.out.println("----------------------------");
        System.out.println("Information about student");
        System.out.println("Current student is bachelor");
        System.out.println("name: " + getName());
        System.out.println("surname: " + getSurname());
        System.out.println("email: " + getEmail());
        System.out.println("course: " + getCourse());
    }
}
