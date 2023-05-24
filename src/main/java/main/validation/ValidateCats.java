package main.validation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCats {
    public static Boolean validateFlyingDream(Scanner scanner) {
        String flyingDream = "";
        Matcher matcher;
        do {
            System.out.print("Enter flyingDream (y - present, n - not present): ");
            flyingDream = scanner.nextLine();
            String regex = "^(?!.*\\d)^[y,n]{1}$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(flyingDream);
            if (!matcher.matches()) {
                System.out.println("flyingDream isn't correct");
                System.out.println("flyingDream must have only alphabet symbols (y or n) and size min 0 and max 1");
            }
        } while (!matcher.matches());
        return flyingDream.equals("y");
    }
}
