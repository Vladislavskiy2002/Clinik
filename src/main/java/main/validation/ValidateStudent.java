package main.validation;

import main.entity.Form;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateStudent {
    public static Form validateType(Scanner scanner){
        while(true) {
            String type ="";
            String studentIdentifyCard = "";
            Integer diplomaId = 0;
            System.out.print("Enter type: ");
            type = scanner.nextLine();

            if (type.equalsIgnoreCase("aspirant")) {
                diplomaId = validateDiplomaId(scanner);
            } else if (type.equalsIgnoreCase("bachelor")) {
                studentIdentifyCard = validateStudentIdentifyCard(scanner);
            } else {
                System.out.println("можна вибрати лише aspirant чи bachelor");
               continue;
            }
            return new Form(type,diplomaId,studentIdentifyCard);
        }
    }
    public static String validateName(Scanner scanner){
        while(true) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("the name must not be empty");
            }
            else
                return name;
        }
    }
    public static String validateSurname(Scanner scanner){
        while(true) {
            System.out.print("Enter surname: ");
            String surname = scanner.nextLine();
            if (surname.isEmpty()) {
                System.out.println("the surname must not be empty");
            }
            else
                return surname;
        }
    }
    public static Integer validateCourse(Scanner scanner){
        while(true) {
            System.out.print("Enter course: ");
            Integer course = scanner.nextInt();
            scanner.nextLine();
            if (course <= 0) {
                System.out.println("the course must be greatest than 0");
            }
            else if (course >= 20) {
                System.out.println("the course must be less or equal 20");
            }
            else
                return course;
        }
    }
    public static Integer validateRes(Scanner scanner){
        while(true) {
            System.out.print("Enter course: ");
            Integer res = scanner.nextInt();
            scanner.nextLine();
            if (res < 0 || res > 3) {
                System.out.println("num must be 0 - 3");
            }
            else
                return res;
        }
    }
    public static Integer validateDiplomaId(Scanner scanner){
        while (true) {
            System.out.print("Enter diplomaId:");
            Integer diplomaId = scanner.nextInt();
            scanner.nextLine();
            if(diplomaId < 0)
            {
                System.out.println("diplomaId must be more than 0");
            }
            else if(diplomaId > 999999)
            {
                System.out.println("diplomaId must be less than 1000000");
            }
            else
                return diplomaId;
        }
    }
    public static String validateStudentIdentifyCard(Scanner scanner){
        while (true) {
            System.out.print("Enter StudentIdentifyCard:");
            String studentIdentifyCard = scanner.nextLine();
            if(studentIdentifyCard.isEmpty())
            {
                System.out.println("must not be empty'");
            }
            else
                return studentIdentifyCard;
        }
    }
    public static String validateEmail(Scanner scanner){
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
