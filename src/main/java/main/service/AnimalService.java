package main.service;

import main.entity.animals.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalService {
    public int findAnimalIdByMedicalIdCard(Animal animal, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id from animal where medical_id_card = ?");
        preparedStatement.setInt(1, animal.getMedicalCardId());
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }
}
