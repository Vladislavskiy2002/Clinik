package main.repository;

import main.entity.Form;
import main.entity.Student;
import main.service.StudentAspirantService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentAspirantRepository extends StudentRepository {
    private final StudentAspirantService service;

    public List<Student> getAllStudents() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student join student_aspirant sa on student.id = sa.student_id"); // select * from student join student_aspirant sa on student.id = sa.student_id
        List<Student> students = new ArrayList<>();
        getService().addAspirantAndBachelorStudentsToListFromResultSet(resultSet, getConnection(), students);
        return students;
    }

    public StudentAspirantRepository(Connection connection) {
        super(connection);
        this.service = new StudentAspirantService();
    }

    public boolean addStudent(Form form) throws SQLException {
        return service.addStudentAspirantToDb(form, getConnection());
    }

    public List<Student> getStudentsByForm(Form form) throws SQLException {
        String selectQuery = "SELECT * from student join student_aspirant sb on student.id = sb.student_id where name = ? AND surname = ? AND course = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(selectQuery);
        preparedStatement.setString(1, form.getName());
        preparedStatement.setString(2, form.getSurname());
        preparedStatement.setInt(3, form.getCourse());

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Student> students = new ArrayList<>();
        getService().addAspirantAndBachelorStudentsToListFromResultSet(resultSet, getConnection(), students);
        return students;
    }
}
