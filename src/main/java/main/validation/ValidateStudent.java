package main.validation;

import main.entity.Form;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateStudent {
    /**
     * Метод validateType - метод який запрошує користувача ввести тип студента та після чого робить перевірку на коректність вводу
     */
    public static Form validateType(Scanner scanner) {
        while (true) {
            String type = "";
            String studentIdentifyCard = "";
            Integer diplomaId = 0;
            System.out.print("Enter type: ");
            type = scanner.nextLine();

            if (type.equalsIgnoreCase("aspirant")) {
                diplomaId = validateDiplomaId(scanner);
            } else if (type.equalsIgnoreCase("bachelor")) {
                studentIdentifyCard = validateStudentIdentifyCard(scanner);
            } else {
                System.out.println("You must choose only aspirant or bachelor");
                continue;
            }
            return new Form(type, diplomaId, studentIdentifyCard);
        }
    }
    /**
     * Метод validateName - метод який запрошує користувача ввести ім'я студента та після чого робить перевірку на коректність вводу
     */
    public static String validateName(Scanner scanner) {
        String name;
        Matcher matcher;
        do {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            String regex = "^(?!.*\\d)^[a-zA-Z]{1,20}$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                System.out.println("name isn't correct");
                System.out.println("name must have only alphabet symbols and size min 1 and max 20");
            }
        } while (!matcher.matches());
        return name;
    }
    /**
     * Метод validateSurname - метод який запрошує користувача ввести фамілію студента та після чого робить перевірку на коректність вводу
     */
    public static String validateSurname(Scanner scanner) {
        String name;
        Matcher matcher;
        do {
            System.out.print("Enter surname: ");
            name = scanner.nextLine();
            String regex = "^(?!.*\\d)^[a-zA-Z]{1,20}$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                System.out.println("Surname isn't correct");
                System.out.println("Surname must have only alphabet symbols and size min 1 and max 20");
            }
        } while (!matcher.matches());
        return name;
    }
    /**
     * Метод validateSurname - метод який запрошує користувача ввести курс студента та після чого робить перевірку на коректність вводу
     */
    public static Integer validateCourse(Scanner scanner) {
        String course;
        Matcher matcher;
        do {
            System.out.print("Enter course: ");
            course = scanner.nextLine();
            String regex = "^(?:[1-9]|1\\d|20)$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(course);
            if (!matcher.matches()) {
                System.out.println("Course isn't correct");
                System.out.println("Course must have only nums and size min 1 and max 20");
            }
        } while (!matcher.matches());
        return Integer.parseInt(course);
    }
    /**
     * Метод validateRes - метод який запрошує користувача ввести число для вибору категорії у меню та після чого робить перевірку на коректність вводу
     */
    public static Integer validateRes(Scanner scanner) {
        String num;
        Matcher matcher;
        do {
            System.out.print("Choose category(0-5): ");
            num = scanner.nextLine();
            String regex = "^[0-5]$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(num);
            if (!matcher.matches()) {
                System.out.println("num isn't correct");
                System.out.println("num must be int and be (0-5)");
            }
        } while (!matcher.matches());
        return Integer.parseInt(num);
    }
    /**
     * Метод validateRes - метод який запрошує користувача ввести номер димлому та після чого робить перевірку на коректність вводу
     */
    public static Integer validateDiplomaId(Scanner scanner) {
        String DiplomaId;
        Matcher matcher;
        do {
            System.out.print("Enter diplomaId: ");
            DiplomaId = scanner.nextLine();
            String regex = "^(?:[1-9]\\d{0,5}|999999)$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(DiplomaId);
            if (!matcher.matches()) {
                System.out.println("diplomaId isn't correct");
                System.out.println("diplomaId must be number and have (1-99999)");
            }
        } while (!matcher.matches());
        return Integer.parseInt(DiplomaId);
    }
    /**
     * Метод validateRes - метод який запрошує користувача ввести студентський квиток та після чого робить перевірку на коректність вводу
     */
    public static String validateStudentIdentifyCard(Scanner scanner) {
        String studentIdentifyCard;
        Matcher matcher;
        do {
            System.out.print("Enter studentIdentifyCard: ");
            studentIdentifyCard = scanner.nextLine();
            String regex = "^[a-zA-Z0-9]{1,15}$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(studentIdentifyCard);
            if (!matcher.matches()) {
                System.out.println("studentIdentifyCard isn't correct");
                System.out.println("studentIdentifyCard must have alphabet symbols and nums and be not more than 15 symbols");
            }
        } while (!matcher.matches());
        return studentIdentifyCard;
    }
    /**
     * Метод validateRes - метод який запрошує користувача ввести email та після чого робить перевірку на коректність вводу
     */
    public static String validateEmail(Scanner scanner) {
        String email;
        Matcher matcher;
        do {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            String regex = "[a-zA-Z0-9]{4,15}@[a-zA-Z]{2,10}.[a-zA-Z]{2,5}";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                System.out.println("email isn't correct");
                System.out.println("email must be like: 'name@gmail.com'");
            }
        } while (!matcher.matches());
        return email;
    }

}
