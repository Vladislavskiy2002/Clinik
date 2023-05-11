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
            System.out.print("type: ");
            type = scanner.nextLine();

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
            return new Form(type,diplomaId,studentIdentifyCard);
        }
    }

    public static String validateEmail(Scanner scanner){
        String email;
        Matcher matcher;
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
        return email;
    }

}
