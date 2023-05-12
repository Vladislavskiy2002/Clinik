package main.ui;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import main.entity.Form;
import main.validation.ValidateStudent;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@NoArgsConstructor
public class Ui {
    private Scanner scanner;

    public int menu() {
        System.out.println("""
                1. Add Student
                2. Show All Students
                3. Find Student
                ---------------
                0. Exit
                """);
        int res = ValidateStudent.validateRes(scanner);
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
}
