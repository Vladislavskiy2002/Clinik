package main.service;

import main.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonService {

    public void addPerson(Connection connection, Person person) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into owner (name, surname,email) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Owner has been created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findPersonIdByEmail(Person person, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id from owner where email = ?");
        preparedStatement.setString(1, person.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }
}
