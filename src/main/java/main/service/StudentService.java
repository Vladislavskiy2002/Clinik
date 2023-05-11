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
                PreparedStatement preparedStatement2 = connection.prepareStatement("select diploma_id from student_aspirant where student_id = ?");
                preparedStatement2.setInt(1, id);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                int diploma_id = 0;
                if (resultSet2.next()) {
                    diploma_id = resultSet2.getInt("diploma_id");
                }
                students.add(new StudentAspirant(id, type, name, surname, email, course, diploma_id));
            } else if (type.equalsIgnoreCase("bachelor")) {
                PreparedStatement preparedStatement2 = connection.prepareStatement("select student_identify_card from student_bachelor where student_id = ?");
                preparedStatement2.setInt(1, id);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                String studentIdentifyCard = "";
                if (resultSet2.next()) {
                    studentIdentifyCard = resultSet2.getString("student_identify_card");
                }
                students.add(new StudentBachelor(id, type, name, surname, email, course, studentIdentifyCard));
            }
        }
    }

    public Boolean addStudentsToDB(Form form, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into student (type, name, surname, email, course) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, form.getType());
            preparedStatement.setString(2, form.getName());
            preparedStatement.setString(3, form.getSurname());
            preparedStatement.setString(4, form.getEmail());
            preparedStatement.setInt(5, form.getCourse());
            preparedStatement.executeUpdate();
            if (form.getType().equalsIgnoreCase("aspirant")) {
                aspirantService.addStudentsToDb(form,connection);
            } else if (form.getType().equalsIgnoreCase("bachelor")) {
                bachelorService.addStudentsToDb(form,connection);
            }

            return true;
        }
    }
}
