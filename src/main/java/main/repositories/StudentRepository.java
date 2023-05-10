package main.repositories;

import lombok.AllArgsConstructor;
import main.tables.Form;
import main.tables.Student;
import main.tables.StudentAspirant;
import main.tables.StudentBachelor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class StudentRepository {
    private Connection connection;

    public boolean addStudent(Form form) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into student (type, name, surname, email, course) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, form.getType());
            preparedStatement.setString(2, form.getName());
            preparedStatement.setString(3, form.getSurname());
            preparedStatement.setString(4, form.getEmail());
            preparedStatement.setInt(5, form.getCourse());
            preparedStatement.executeUpdate();
            System.out.println("fdfdfdfd");
            return true;
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            Integer course = resultSet.getInt("course");

            if (type.equalsIgnoreCase("aspirant")) {
                students.add(new StudentBachelor(id, type, name, surname, email, course));
            } else if (type.equalsIgnoreCase("bachelor")) {
                students.add(new StudentAspirant(id, type, name, surname, email, course));
            }
        }
        return students;
    }

    public List<Student> getStudentsByForm(Form form) throws SQLException {
        Statement statement = connection.createStatement();
        String selectQuery = "SELECT * from student where name = ? AND surname = ? AND course = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, form.getName());
        preparedStatement.setString(2, form.getSurname());
        preparedStatement.setInt(3, form.getCourse());

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Student> students = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            int course = resultSet.getInt("course");
            if (type.equalsIgnoreCase("aspirant")) {
                students.add(new StudentBachelor(id, type, name, surname, email, course));
            } else if (type.equalsIgnoreCase("bachelor")) {
                students.add(new StudentAspirant(id, type, name, surname, email, course));
            }
        }
        // Close the result set and statement
        resultSet.close();
        preparedStatement.close();
        return students;
    }
}
