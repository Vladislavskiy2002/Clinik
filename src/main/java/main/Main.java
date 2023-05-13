package main;

import main.repository.StudentAspirantRepository;
import main.repository.StudentRepository;
import main.ui.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    private StudentRepository studentRepository;

    public Main() {
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {

        try (BufferedReader reader = Files.newBufferedReader(Path.of("StudentDatabaseManagementSystem.properties"))) {
            Properties props = new Properties();
            props.load(reader);
            Connection connection = DriverManager.getConnection(props.getProperty("url"), props);

            studentRepository = new StudentAspirantRepository(connection);
            Ui ui = new Ui(connection);
            ui.runUi();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}