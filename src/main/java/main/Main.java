package main;

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
    public Main() {
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
    private void run() {

        try (BufferedReader reader = Files.newBufferedReader(Path.of("Clinik.properties"))) {
            Properties props = new Properties();
            props.load(reader);
            Connection connection = DriverManager.getConnection(props.getProperty("url"), props);

            Ui ui = new Ui(connection);
            ui.runUi();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}