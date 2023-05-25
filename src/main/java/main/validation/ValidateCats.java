package main.validation;

import main.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCats {
    /**
     * Метод validateFlyingDream - статичний метод класу ValidateCats який валідує FlyingDream
     */
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
