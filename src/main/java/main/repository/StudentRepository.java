package main.repository;

import lombok.Data;
import main.entity.Form;
import main.entity.Student;
import main.service.StudentService;

import java.sql.Connection;
import java.sql.SQLException;
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

    abstract public List<Student> getAllStudents() throws SQLException;

    abstract public List<Student> getStudentsByForm(Form form) throws SQLException;
}
