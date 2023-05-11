package main.repository;

import main.entity.Form;
import main.entity.Student;
import main.service.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private Connection connection;
    private StudentService service;

    public StudentRepository() {
    }

    public StudentRepository(Connection connection) {
        this.connection = connection;
        this.service = new StudentService();
    }

    public boolean addStudent(Form form) throws SQLException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(
//                "insert into student (type, name, surname, email, course) VALUES (?, ?, ?, ?, ?)")) {
//            preparedStatement.setString(1, form.getType());
//            preparedStatement.setString(2, form.getName());
//            preparedStatement.setString(3, form.getSurname());
//            preparedStatement.setString(4, form.getEmail());
//            preparedStatement.setInt(5, form.getCourse());
//            preparedStatement.executeUpdate();
//            if (form.getType().equalsIgnoreCase("aspirant")) {
//                String selectQuery = "SELECT id from student where email = ?";
//                PreparedStatement preparedStatement2 = connection.prepareStatement(selectQuery);
//                preparedStatement2.setString(1, form.getEmail());
//                ResultSet resultSet2 = preparedStatement2.executeQuery();
//                int id = 0;
//                if (resultSet2.next()) {
//                    id = resultSet2.getInt("id");
//                }
//                preparedStatement2 = connection.prepareStatement("insert into student_aspirant(student_id, diploma_id) VALUES (?,?)");
//                    preparedStatement2.setInt(1,id);
//                    preparedStatement2.setInt(2,form.getDiplomaID());
//                    preparedStatement2.executeUpdate();
//            } else if (form.getType().equalsIgnoreCase("bachelor")) {
//                String selectQuery = "SELECT id from student where email = ?";
//                PreparedStatement preparedStatement2 = connection.prepareStatement(selectQuery);
//                preparedStatement2.setString(1, form.getEmail());
//                ResultSet resultSet2 = preparedStatement2.executeQuery();
//                int id = 0;
//                if (resultSet2.next()) {
//                    id = resultSet2.getInt("id");
//                }
//                preparedStatement2 = connection.prepareStatement("insert into student_bachelor(student_id, student_identify_card) VALUES (?,?)");
//                    preparedStatement2.setInt(1,id);
//                    preparedStatement2.setString(2,form.getStudentIdentifyCard());
//                    preparedStatement2.executeUpdate();
//            }
//
//            return true;
//        }
        return service.addStudentsToDB(form, connection);
    }

    public List<Student> getAllStudents() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        List<Student> students = new ArrayList<>();
        service.addAspirantAndBachelorStudentsToListFromResultSet(resultSet, connection, students);
//        while (resultSet.next()) {
//            Integer id = resultSet.getInt("id");
//            String type = resultSet.getString("type");
//            String name = resultSet.getString("name");
//            String surname = resultSet.getString("surname");
//            String email = resultSet.getString("email");
//            Integer course = resultSet.getInt("course");
//
//            if (type.equalsIgnoreCase("aspirant")) {
//                PreparedStatement preparedStatement2 = connection.prepareStatement("select diploma_id from student_aspirant where student_id = ?");
//                preparedStatement2.setInt(1,id);
//                ResultSet resultSet2 = preparedStatement2.executeQuery();
//                int diploma_id = 0;
//                if (resultSet2.next()) {
//                    diploma_id = resultSet2.getInt("diploma_id");
//                }
//                students.add(new StudentAspirant(id, type, name, surname, email, course, diploma_id));
//            } else if (type.equalsIgnoreCase("bachelor")) {
//                PreparedStatement preparedStatement2 = connection.prepareStatement("select student_identify_card from student_bachelor where student_id = ?");
//                preparedStatement2.setInt(1,id);
//                ResultSet resultSet2 = preparedStatement2.executeQuery();
//                String studentIdentifyCard = "";
//                if (resultSet2.next()) {
//                    studentIdentifyCard = resultSet2.getString("student_identify_card");
//                }
//                students.add(new StudentBachelor(id, type, name, surname, email, course, studentIdentifyCard));
//            }
//        }
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
//        while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String type = resultSet.getString("type");
//            String name = resultSet.getString("name");
//            String surname = resultSet.getString("surname");
//            String email = resultSet.getString("email");
//            int course = resultSet.getInt("course");
//
//            if (type.equalsIgnoreCase("bachelor")) {
//                PreparedStatement preparedStatement2 = connection.prepareStatement("select student_identify_card from student_bachelor where student_id = ?");
//                preparedStatement2.setInt(1,id);
//                ResultSet resultSet2 = preparedStatement2.executeQuery();
//                String studentIdentifyCard = "";
//                if (resultSet2.next()) {
//                    studentIdentifyCard = resultSet2.getString("student_identify_card");
//                }
//                students.add(new StudentBachelor(id, type, name, surname, email, course, studentIdentifyCard));
//            } else if (type.equalsIgnoreCase("aspirant")) {
//                PreparedStatement preparedStatement2 = connection.prepareStatement("select diploma_id from student_aspirant where student_id = ?");
//                preparedStatement2.setInt(1,id);
//                ResultSet resultSet2 = preparedStatement2.executeQuery();
//                int diploma_id = 0;
//                if (resultSet2.next()) {
//                    diploma_id = resultSet2.getInt("diploma_id");
//                }
//                students.add(new StudentAspirant(id, type, name, surname, email, course, diploma_id));
//            }
//        }
        return students;
    }
}
