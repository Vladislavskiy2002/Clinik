package main.util;

import main.entity.Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
    public static void addStudentWithTypeToDb(String query, Form form, Connection connection) throws SQLException {
        String selectQuery = "SELECT id from student where email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, form.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        if (form.getType().equalsIgnoreCase("aspirant"))
            preparedStatement.setInt(2, form.getDiplomaID());
        else if (form.getType().equalsIgnoreCase("bachelor")) {
            preparedStatement.setString(2, form.getStudentIdentifyCard());
        }
        preparedStatement.executeUpdate();
    }
}
