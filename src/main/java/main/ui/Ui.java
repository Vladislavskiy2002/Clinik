package main.ui;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import main.tables.Form;

import java.util.Scanner;

@AllArgsConstructor
@NoArgsConstructor
public class Ui {
    private Scanner scanner;

    public int menu() {
        System.out.println("""
                1. Add Student
                2. Show All Students
                3. Find Student
                ---------------
                0. Exit
                """);
        int res = scanner.nextInt();
        scanner.nextLine();
        return res;
    }

    public Form inputStudentData() {
        while (true) {
            System.out.print("type: ");
            String type = scanner.nextLine();
            if (!type.equalsIgnoreCase("aspirant") && !type.equalsIgnoreCase("bachelor")) {
                System.out.println("можна вибрати лише aspirant чи bachelor");
                continue;
            }
            System.out.print("name: ");
            String name = scanner.nextLine();
            System.out.print("surname: ");
            String surname = scanner.nextLine();
            System.out.print("email: ");
            String email = scanner.nextLine();
            System.out.print("course: ");
            Integer course = scanner.nextInt();
            System.out.println(course);
            return new Form(type, name, surname, email, course);
        }
    }

    public Form findStudentsData() {
        System.out.print("name: ");
        String name = scanner.nextLine();
        System.out.print("surname: ");
        String surname = scanner.nextLine();
        System.out.print("course: ");
        Integer course = scanner.nextInt();
        System.out.println(course);
        return new Form(name, surname, course);
    }
}
