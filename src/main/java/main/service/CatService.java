package main.service;

import main.entity.Person;
import main.entity.animals.Cat;
import main.validation.ValidateAnimals;
import main.validation.ValidateCats;
import main.validation.ValidateDogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CatService extends AnimalService {
    /**
     * Метод addCat - метод класу CatService який знаходить у таблиці animal - id за medical_id_card та після дадає данні з animal id та flying_dream(з cat) в таблицю cats
     */
    public void addCat(Cat cat, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id from animal where medical_id_card = ?");
        preparedStatement.setInt(1, cat.getMedicalCardId());
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        preparedStatement = connection.prepareStatement("insert into cats(animal_id, flying_dream) VALUES (?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setBoolean(2, cat.getFlyingDream());
        preparedStatement.executeUpdate();
    }
    /**
     * Метод showCats - метод класу CatService який знаходить котів заданого користувача та виводить їх на екран
     */
    public void showCats(Person person, Connection connection) throws SQLException {
        String selectQuery = "select * from clinic join cats d on clinic.animal_id = d.animal_id join animal a on clinic.animal_id = a.id where owner_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, person.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String date = resultSet.getString("date");
            String name = resultSet.getString("name");
            Integer age = resultSet.getInt("age");
            Boolean flyingDream = resultSet.getBoolean("flying_dream");
            String ill = resultSet.getString("ill");

            System.out.println("--------------------------------------");
            System.out.println("\tCat's information");
            System.out.println("\tName: " + name);
            System.out.println("\tAge: " + age);
            System.out.println("\tFlying dream: " + flyingDream);
            System.out.println("\tDate of registration: " + date);
            System.out.println("\tIll: " + ill);
        }
    }
}
