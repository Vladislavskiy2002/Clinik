package main.service;

import main.entity.Form;
import main.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentAspirantService {

    public boolean addStudentAspirantToDb(Form form, Connection connection) throws SQLException {
        StudentService studentService = new StudentService();
        studentService.addStudentsToDb(form, connection);
        Util.addStudentWithTypeToDb("insert into student_aspirant(student_id, diploma_id) VALUES (?,?)", form, connection);
        return true;
    }

    public Integer findDiplomaIdFromDb(Integer studentId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select diploma_id from student_aspirant where student_id = ?");
        preparedStatement.setInt(1, studentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        int diploma_id = 0;
        return resultSet.next() ? resultSet.getInt("diploma_id") : diploma_id;
    }
}
