package main.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateOwners {
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
    public static Boolean validateIfCurrentOwnerExist(final String email, Connection connection) throws SQLException {
        String selectQuery = "SELECT email from owner where email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("Current owner is already exist");
            return true;
        }
        return false;
    }
}
