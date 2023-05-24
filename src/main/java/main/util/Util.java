package main.util;

import main.entity.animals.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {
    public static void addAnimal(Animal animal, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into animal (name, age, medical_id_card) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setInt(2, animal.getAge());
            preparedStatement.setInt(3, animal.getMedicalCardId());
            preparedStatement.executeUpdate();
        }
    }
}
