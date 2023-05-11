package main.service;

import main.entity.Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentBachelorService {
    public void addStudentsToDb(Form form, Connection connection) throws SQLException {
        String selectQuery = "SELECT id from student where email = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(selectQuery);
        preparedStatement2.setString(1, form.getEmail());
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        int id = 0;
        if (resultSet2.next()) {
            id = resultSet2.getInt("id");
        }
        preparedStatement2 = connection.prepareStatement("insert into student_bachelor(student_id, student_identify_card) VALUES (?,?)");
        preparedStatement2.setInt(1, id);
        preparedStatement2.setString(2, form.getStudentIdentifyCard());
        preparedStatement2.executeUpdate();
    }
}