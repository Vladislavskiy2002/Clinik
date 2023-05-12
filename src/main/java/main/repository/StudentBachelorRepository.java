package main.repository;

import main.entity.Form;
import main.entity.Student;
import main.service.StudentBachelorService;
import main.service.StudentService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentBachelorRepository extends StudentRepository{
    private StudentBachelorService service;

    public StudentBachelorRepository(Connection connection) {
        super(connection);
        this.service = new StudentBachelorService();
    }

    public boolean addStudent(Form form) throws SQLException {
        return service.addStudentBachelorToDb(form,getConnection());
    }
}
