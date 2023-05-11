package main.ui;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import main.entity.Form;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            int diplomaId = 0;
            String studentIdentifyCard = "";
            if (type.equalsIgnoreCase("aspirant")) {
                System.out.print("DiplomaId:");
                diplomaId = scanner.nextInt();
                scanner.nextLine();
            } else if (type.equalsIgnoreCase("bachelor")) {
                System.out.print("StudentIdentifyCard:");
                studentIdentifyCard = scanner.nextLine();
            } else {
                System.out.println("можна вибрати лише aspirant чи bachelor");
                continue;
            }
            System.out.print("name: ");
            String name = scanner.nextLine();
            System.out.print("surname: ");
            String surname = scanner.nextLine();
            Matcher matcher;
            String email;
            do {
                System.out.print("email: ");
                email = scanner.nextLine();
                String regex = "[a-zA-Z0-9]{4,15}@[a-zA-Z]{2,10}.[a-zA-Z]{2,5}";
                Pattern pattern = Pattern.compile(regex);
                matcher = pattern.matcher(email);
                if (!matcher.matches()) {
                    System.out.println("email isn't correct");
                    System.out.println("email must be like: 'name@gmail.com'");
                }
            } while (!matcher.matches());
            System.out.print("course: ");
            Integer course = scanner.nextInt();
            return new Form(type, name, surname, email, course, diplomaId, studentIdentifyCard);
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
