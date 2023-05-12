package main.service;

import main.entity.Form;
import main.entity.Student;
import main.entity.StudentAspirant;
import main.entity.StudentBachelor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private StudentBachelorService bachelorService;
    private StudentAspirantService aspirantService;

    public StudentService() {
        this.bachelorService = new StudentBachelorService();
        this.aspirantService = new StudentAspirantService();
    }

    public void addAspirantAndBachelorStudentsToListFromResultSet(ResultSet resultSet, Connection connection, List<Student> students) throws SQLException {
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Integer id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            Integer course = resultSet.getInt("course");

            if (type.equalsIgnoreCase("aspirant")) {
                Integer diploma_id = aspirantService.findDiplomaIdFromDb(id,connection);
                students.add(new StudentAspirant(id, type, name, surname, email, course, diploma_id));
            } else if (type.equalsIgnoreCase("bachelor")) {
                String studentIdentifyCard = bachelorService.findStudentIdentifyCardFromDb(id,connection);
                students.add(new StudentBachelor(id, type, name, surname, email, course, studentIdentifyCard));
            }
        }
    }

    public Boolean addStudentsToDb(Form form, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into student (type, name, surname, email, course) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, form.getType());
            preparedStatement.setString(2, form.getName());
            preparedStatement.setString(3, form.getSurname());
            preparedStatement.setString(4, form.getEmail());
            preparedStatement.setInt(5, form.getCourse());
            preparedStatement.executeUpdate();
            return true;
        }
    }
}
