package main;

import main.entity.Form;
import main.entity.Student;
import main.entity.StudentBachelor;
import main.repository.StudentAspirantRepository;
import main.repository.StudentBachelorRepository;
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
    private StudentBachelorRepository studentBachelorRepository;
    private StudentAspirantRepository studentAspirantRepository;

    public Main() {
    }

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
            //studentRepository = new StudentRepository(connection);
            studentBachelorRepository = new StudentBachelorRepository(connection);
            studentAspirantRepository = new StudentAspirantRepository(connection);

            Ui ui = new Ui(new Scanner(System.in));

            List<Student> students;
            int m;
            while ((m = ui.menu()) != 0) {
                switch (m) {
                    case ADD_STUDENT -> {
                        //studentRepository.addStudent(ui.inputStudentData());
                        Form form = ui.inputStudentData();
                        if(form.getType().equalsIgnoreCase("aspirant")) {
                            studentRepository = new StudentAspirantRepository(connection);
                            studentAspirantRepository.addStudent(form);
                        }
                        else if(form.getType().equalsIgnoreCase("bachelor")){
                            studentRepository = new StudentBachelorRepository(connection);
                            studentBachelorRepository.addStudent(form);
                        }
                        else{
                            throw new RuntimeException();
                        }
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