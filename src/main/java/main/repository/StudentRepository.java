package main.repository;

import lombok.Data;
import main.entity.Form;
import main.entity.Student;
import main.service.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
abstract public class StudentRepository {
    private Connection connection;
    private StudentService service;

    public StudentRepository(Connection connection) {
        this.connection = connection;
        this.service = new StudentService();
    }

    public abstract boolean addStudent(Form form) throws SQLException;

    public List<Student> getAllStudents() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        List<Student> students = new ArrayList<>();
        service.addAspirantAndBachelorStudentsToListFromResultSet(resultSet, connection, students);
        return students;
    }

    public List<Student> getStudentsByForm(Form form) throws SQLException {
        String selectQuery = "SELECT * from student where name = ? AND surname = ? AND course = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, form.getName());
        preparedStatement.setString(2, form.getSurname());
        preparedStatement.setInt(3, form.getCourse());

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Student> students = new ArrayList<>();
        service.addAspirantAndBachelorStudentsToListFromResultSet(resultSet, connection, students);
        return students;
    }
}
