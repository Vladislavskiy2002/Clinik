package main.service;

import main.entity.Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentAspirantService {

    public boolean addStudentAspirantToDb(Form form, Connection connection) throws SQLException {
        StudentService studentService = new StudentService();
        studentService.addStudentsToDb(form,connection);
        String selectQuery = "SELECT id from student where email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, form.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        preparedStatement = connection.prepareStatement("insert into student_aspirant(student_id, diploma_id) VALUES (?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, form.getDiplomaID());
        preparedStatement.executeUpdate();
        return true;
    }
    public Integer findDiplomaIdFromDb(Integer id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement2 = connection.prepareStatement("select diploma_id from student_aspirant where student_id = ?");
        preparedStatement2.setInt(1, id);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        int diploma_id = 0;
        if (resultSet2.next()) {
            diploma_id = resultSet2.getInt("diploma_id");
        }
        return diploma_id;
    }
}
