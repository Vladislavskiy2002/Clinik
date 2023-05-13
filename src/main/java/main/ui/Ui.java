package main.ui;

import main.entity.Form;
import main.entity.Student;
import main.repository.StudentAspirantRepository;
import main.repository.StudentBachelorRepository;
import main.repository.StudentRepository;
import main.validation.ValidateStudent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static main.util.MyConstants.*;

public class Ui {
    private final Scanner scanner;
    private final StudentAspirantRepository studentAspirantRepository;
    private final StudentBachelorRepository studentBachelorRepository;

    public Ui(Connection connection) {
        studentAspirantRepository = new StudentAspirantRepository(connection);
        studentBachelorRepository = new StudentBachelorRepository(connection);
        scanner = new Scanner(System.in);
    }

    public int getMenuOption() {
        System.out.println("""
                1. Add Student
                2. Show Aspirant student
                3. Show Bachelor student
                4. Find Aspirant student
                5. Find Bachelor student
                ---------------
                0. Exit
                """);
        Integer res = ValidateStudent.validateRes(scanner);
        return res;
    }

    public Form inputStudentData() {
        Form form = ValidateStudent.validateType(scanner);
        String name = ValidateStudent.validateName(scanner);
        String surname = ValidateStudent.validateSurname(scanner);
        String email = ValidateStudent.validateEmail(scanner);
        Integer course = ValidateStudent.validateCourse(scanner);
        return new Form(form.getType(), name, surname, email, course, form.getDiplomaID(), form.getStudentIdentifyCard());
    }

    public Form findStudentsData() {
        String name = ValidateStudent.validateName(scanner);
        String surname = ValidateStudent.validateSurname(scanner);
        Integer course = ValidateStudent.validateCourse(scanner);
        return new Form(name, surname, course);
    }

    public void runUi() throws SQLException {
        List<Student> students;
        int menuOption;
        while ((menuOption = getMenuOption()) != 0) {
            switch (menuOption) {
                case ADD_STUDENT -> {
                    Form form = inputStudentData();
                    if (form.getType().equalsIgnoreCase("aspirant")) {
                        studentAspirantRepository.addStudent(form);
                    } else if (form.getType().equalsIgnoreCase("bachelor")) {
                        studentBachelorRepository.addStudent(form);
                    } else {
                        throw new RuntimeException();
                    }
                }
                case SHOW_ALL_ASPIRANT_STUDENTS -> {
                    studentAspirantRepository.getAllStudents().forEach(Student::show);
                }
                case SHOW_ALL_BACHELOR_STUDENTS -> {
                    studentBachelorRepository.getAllStudents().forEach(Student::show);
                }
                case SHOW_ASPIRANT_BY_FORM -> {
                    studentAspirantRepository.getStudentsByForm(findStudentsData()).forEach(Student::show);
                }
                case SHOW_BACHELOR_BY_FORM -> {
                    studentBachelorRepository.getStudentsByForm(findStudentsData()).forEach(Student::show);
                }
            }
        }
    }
}
