package main.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDogs {
    public static Boolean validateIfCurrentDogExist(Integer medicalIdCard, Connection connection) throws SQLException {
        String selectQuery = "SELECT * from animal join dogs c on animal.id = c.animal_id where medical_id_card = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, medicalIdCard);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        } else {
            System.out.println("Dog with current data isn't exist");
        }
        return false;
    }

    public static String validateType(Scanner scanner) {
        String type;
        Matcher matcher;
        do {
            System.out.print("Enter type: ");
            type = scanner.nextLine();
            String regex = "^[a-zA-Z]{1,20}$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(type);
            if (!matcher.matches()) {
                System.out.println("type isn't correct");
                System.out.println("type must have size min 1 and max 20");
            }
        } while (!matcher.matches());
        return type;
    }
}
