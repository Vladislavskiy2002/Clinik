package main;

import main.entity.Student;
import main.repository.StudentRepository;
import main.ui.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static main.util.MyConstants.*;

public class Main {
    private Scanner scanner;
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        //init();

        try (BufferedReader reader = Files.newBufferedReader(Path.of("StudentDatabaseManagementSystem.properties"))) {
            Properties props = new Properties();
            props.load(reader);
            Connection connection = DriverManager.getConnection(props.getProperty("url"), props);
            studentRepository = new StudentRepository(connection);

            Ui ui = new Ui(new Scanner(System.in));

            List<Student> students;
            int m;
            while ((m = ui.menu()) != 0) {
                switch (m) {
                    case ADD_STUDENT -> {
                        studentRepository.addStudent(ui.inputStudentData());
                    }
                    case SHOW_ALL_STUDENTS -> {
                        students = studentRepository.getAllStudents();
                        for (Student student : students) {
                            student.show();
                        }
                    }
                    case SHOW_STUDENT_BY_FORM -> {
                        students = studentRepository.getStudentsByForm(ui.findStudentsData());
                        for (Student student : students) {
                            student.show();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}