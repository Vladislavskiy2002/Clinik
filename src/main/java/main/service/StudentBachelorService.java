package main.service;

import main.entity.Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentBachelorService {
    public boolean addStudentBachelorToDb(Form form, Connection connection) throws SQLException {
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
        preparedStatement = connection.prepareStatement("insert into student_bachelor(student_id, student_identify_card) VALUES (?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, form.getStudentIdentifyCard());
        preparedStatement.executeUpdate();
        return true;
    }

    public String findStudentIdentifyCardFromDb(Integer id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement2 = connection.prepareStatement("select student_identify_card from student_bachelor where student_id = ?");
        preparedStatement2.setInt(1, id);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        String studentIdentifyCard = "";
        if (resultSet2.next()) {
            studentIdentifyCard = resultSet2.getString("student_identify_card");
        }
        return studentIdentifyCard;
    }
}
