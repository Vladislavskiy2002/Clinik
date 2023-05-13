package main.repository;

import main.entity.Form;
import main.entity.Student;
import main.service.StudentBachelorService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentBachelorRepository extends StudentRepository {
    private final StudentBachelorService service;

    public StudentBachelorRepository(Connection connection) {
        super(connection);
        this.service = new StudentBachelorService();
    }

    /**
     * Метод getAllStudents - перевизначений метод абстрактного класу StudentRepository який повертає усіх студентів які є Бакалаврами
     * У цьому методі ми створюємо Query у якій беремо усіх студентів бакалаврів і присвоюємо її в resultSet
     * Після чого ми створюємо List студентів
     * передаємо resultSet, connection та силку на List через аргументи методу addAspirantAndBachelorStudentsToListFromResultSet
     * І в кінці повертаємо наших студентів
     */
    public List<Student> getAllStudents() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student join student_bachelor sb on student.id = sb.student_id"); // select * from student join student_aspirant sa on student.id = sa.student_id
        List<Student> students = new ArrayList<>();
        getService().addAspirantAndBachelorStudentsToListFromResultSet(resultSet, getConnection(), students);
        return students;
    }
    /**
     * Метод addStudent - перевизначений метод абстрактного класу StudentRepository який додає студентів бакалаврів
     * У цьому методі ми викликаємо метод addStudentBachelorToDb зі StudentBachelorService та передаємо йому в аргументи Form та Connection
     */
    public boolean addStudent(Form form) throws SQLException {
        return service.addStudentBachelorToDb(form, getConnection());
    }

    /**
     * Метод getStudentsByForm - перевизначений метод абстрактного класу StudentRepository який шукає студентів бакалаврів за form
     * У цьому методі прописуємо Query у якій беремо усіх студентів бакалаврів - name, surname та course яких відповідають form
     * викликаємо метод addAspirantAndBachelorStudentsToListFromResultSet в який передаємо Query, і який додасть знайдених студентів у List
     */
    public List<Student> getStudentsByForm(Form form) throws SQLException {
        String selectQuery = "SELECT * from student join student_bachelor sb on student.id = sb.student_id where name = ? AND surname = ? AND course = ?";
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
