package main.repository;

import main.entity.Form;
import main.entity.Student;
import main.service.StudentAspirantService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentAspirantRepository extends StudentRepository {
    private final StudentAspirantService service;

    public StudentAspirantRepository(Connection connection) {
        super(connection);
        this.service = new StudentAspirantService();
    }
    /**
     * Метод getAllStudents - перевизначений метод абстрактного класу StudentRepository який повертає усіх студентів які є Аспірантами
     * У цьому методі ми створюємо Query у якій беремо усіх студентів аспірантів і присвоюємо її в resultSet
     * Після чого ми створюємо List студентів
     * передаємо resultSet, connection та силку на List через аргументи методу addAspirantAndBachelorStudentsToListFromResultSet
     * І в кінці повертаємо наших студентів
     */
    public List<Student> getAllStudents() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student join student_aspirant sa on student.id = sa.student_id"); // select * from student join student_aspirant sa on student.id = sa.student_id
        List<Student> students = new ArrayList<>();
        getService().addAspirantAndBachelorStudentsToListFromResultSet(resultSet, getConnection(), students);
        return students;
    }
    /**
     * Метод addStudent - перевизначений метод абстрактного класу StudentRepository який додає студентів Аспірантів
     * У цьому методі ми викликаємо метод addStudentAspirantToDb зі StudentAspirantService та передаємо йому в аргументи Form та Connection
     */
    public boolean addStudent(Form form) throws SQLException {
        return service.addStudentAspirantToDb(form, getConnection());
    }
    /**
     * Метод getStudentsByForm - перевизначений метод абстрактного класу StudentRepository який шукає студентів аспірантів за form
     * У цьому методі прописуємо Query у якій беремо усіх студентів аспірантів - name, surname та course яких відповідають form
     * викликаємо метод addAspirantAndBachelorStudentsToListFromResultSet в який передаємо Query, і який додасть знайдених студентів у List
     */
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
