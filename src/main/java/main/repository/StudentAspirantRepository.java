package main.repository;

import main.entity.Form;
import main.entity.Student;
import main.service.StudentAspirantService;
import main.service.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentAspirantRepository extends StudentRepository {
    private StudentAspirantService service;

    public StudentAspirantRepository(Connection connection) {
        super(connection);
        this.service = new StudentAspirantService();
    }

    public boolean addStudent(Form form) throws SQLException {
        return service.addStudentAspirantToDb(form, getConnection());
    }
}
