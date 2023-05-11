package main.service;

import main.entity.Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentAspirantService {
    public void addStudentsToDb(Form form, Connection connection) throws SQLException {
        String selectQuery = "SELECT id from student where email = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(selectQuery);
        preparedStatement2.setString(1, form.getEmail());
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        int id = 0;
        if (resultSet2.next()) {
            id = resultSet2.getInt("id");
        }
        preparedStatement2 = connection.prepareStatement("insert into student_aspirant(student_id, diploma_id) VALUES (?,?)");
        preparedStatement2.setInt(1, id);
        preparedStatement2.setInt(2, form.getDiplomaID());
        preparedStatement2.executeUpdate();
    }
}
