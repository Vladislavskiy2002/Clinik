package main.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateAnimals {
    /**
     * Метод validateName - статичний метод класу ValidateAnimals який валідує імя
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
     * Метод validateIll - статичний метод класу ValidateAnimals який валідує хворобу
     */
    public static String validateIll(Scanner scanner) {
        String ill;
        Matcher matcher;
        do {
            System.out.print("Enter ill: ");
            ill = scanner.nextLine();
            String regex = "^.{1,100}$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(ill);
            if (!matcher.matches()) {
                System.out.println("ill isn't correct");
                System.out.println("ill must have size min 1 and max 100 symbols");
            }
        } while (!matcher.matches());
        return ill;
    }
    /**
     * Метод validateRes - статичний метод класу ValidateAnimals який число яке ми вводимо в меню
     */
    public static Integer validateRes(Scanner scanner) {
        String num;
        Matcher matcher;
        do {
            System.out.print("Choose category(0-7): ");
            num = scanner.nextLine();
            String regex = "^[0-667]$";
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
     * Метод validateIfCurrentCatExist - статичний метод класу ValidateAnimals який перевіряє за medicalIdCard чи існує кіт в таблиці
     */
    public static Boolean validateIfCurrentCatExist(Integer medicalIdCard, Connection connection) throws SQLException {
        String selectQuery = "SELECT * from animal join cats c on animal.id = c.animal_id where medical_id_card = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, medicalIdCard);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        } else {
            System.out.println("Cat with current data isn't exist");
        }
        return false;
    }
    /**
     * Метод validateAge - статичний метод класу ValidateAnimals який валідує число age
     */
    public static Integer validateAge(Scanner scanner) {
        String age;
        Matcher matcher;
        do {
            System.out.print("Enter age: ");
            age = scanner.nextLine();
            String regex = "^(?:[1-9]|1\\d|20)$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(age);
            if (!matcher.matches()) {
                System.out.println("Age isn't correct");
                System.out.println("Age must have only nums and size min 1 and max 20");
            }
        } while (!matcher.matches());
        return Integer.parseInt(age);
    }

    /**
     * Метод validateIfCurrentAnimalExist - статичний метод класу ValidateAnimals який перевіряє за medicalIdCard чи існує animal в таблиці
     */
    public static Boolean validateIfCurrentAnimalExist(Integer medicalIdCard, Connection connection) throws SQLException {
        String selectQuery = "SELECT animal.id from animal where medical_id_card = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, medicalIdCard);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getInt("id") == 0) {
                return false;
            } else {
                System.out.println("Pet with current medicalIdCard is already exist");
                return true;
            }
        }
        return false;
    }
    /**
     * Метод validateMedicalCardId - статичний метод класу ValidateAnimals який валідує MedicalCardId
     */
    public static Integer validateMedicalCardId(Scanner scanner, Connection connection) throws SQLException {
        String medicalCardId = "";
        Matcher matcher;
        do {
            System.out.print("Enter medicalCardId: ");
            medicalCardId = scanner.nextLine();
            String regex = "^[1-9]{1,8}$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(medicalCardId);
            if (!matcher.matches()) {
                System.out.println("medicalCardId isn't correct");
                System.out.println("medicalCardId must have only nums and size min 1 and max 99999999");
            }
        } while (!matcher.matches());
        return Integer.parseInt(medicalCardId);
    }
    /**
     * Метод validateOnExistMedicalCardId - статичний метод класу ValidateAnimals який перевіряє та валідує чи існує задана MedicalCardId
     */
    public static Integer validateOnExistMedicalCardId(Scanner scanner, Connection connection) throws SQLException {
        Boolean isExist = true;
        Integer medicalCardId = 0;
        while (isExist) {
            medicalCardId = validateMedicalCardId(scanner, connection);
            isExist = validateIfCurrentAnimalExist(medicalCardId, connection);
        }
        return medicalCardId;
    }
}
