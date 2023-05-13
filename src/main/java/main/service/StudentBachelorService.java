package main.service;

import main.entity.Form;
import main.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentBachelorService {
    /**
     * Метод addStudentBachelorToDb - метод який додає студентів бакалаврів
     * У цьому методі ми викликаємо методи які додають данні студентів в таблицю student та student_bachelor
     */
    public boolean addStudentBachelorToDb(Form form, Connection connection) throws SQLException {
        StudentService studentService = new StudentService();
        studentService.addStudentsToDb(form, connection);
        Util.addStudentWithTypeToDb("insert into student_bachelor(student_id, student_identify_card) VALUES (?,?)", form, connection);
        return true;
    }
    /**
     * Метод findStudentIdentifyCardFromDb - метод шукає студентський квиток за studentId
     */
    public String findStudentIdentifyCardFromDb(Integer studentId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select student_identify_card from student_bachelor where student_id = ?");
        preparedStatement.setInt(1, studentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        String studentIdentifyCard = "";
        return resultSet.next() ? resultSet.getString("student_identify_card") : studentIdentifyCard;
    }
}
