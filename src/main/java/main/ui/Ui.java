package main.ui;

import main.entity.Form;
import main.entity.Student;
import main.repository.StudentAspirantRepository;
import main.repository.StudentBachelorRepository;
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
    /**
     * Метод getMenuOption - метод який виводить у консоль меню опцій
     * та чекає на ввід в консоль число яке є від 1-5
     */
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
    /**
     * Метод inputStudentData - метод який дозволяє користувачу ввести коректні данні про студента при його додаванні
     */
    public Form inputStudentData() {
        Form form = ValidateStudent.validateType(scanner);
        String name = ValidateStudent.validateName(scanner);
        String surname = ValidateStudent.validateSurname(scanner);
        String email = ValidateStudent.validateEmail(scanner);
        Integer course = ValidateStudent.validateCourse(scanner);
        return new Form(form.getType(), name, surname, email, course, form.getDiplomaID(), form.getStudentIdentifyCard());
    }
    /**
     * Метод findStudentsData - метод який дозволяє користувачу ввести коректні данні про студента при його пошуку
     */
    public Form findStudentsData() {
        String name = ValidateStudent.validateName(scanner);
        String surname = ValidateStudent.validateSurname(scanner);
        Integer course = ValidateStudent.validateCourse(scanner);
        return new Form(name, surname, course);
    }
    /**
     * Метод runUi - метод який реалізує запуск логіки програми
     * у ньому ми для menuOption присвоюємо результат з методу getMenuOption()
     * ЯКЩО menuOption 0 - то ми реалізуємо вихід з програми через виконану умову while
     * ЯКЩО menuOption 1 - то виконується умова case ADD_STUDENT у якій ми викликаємо метод inputStudentData() який повертає форму яку заповнює користувач
     *      після в залежності від type у формі ми викликаємо додавання студента
     * ЯКЩО menuOption 2 - то ми викликаємо у studentAspirantRepository метод getAllStudents та після чого виводимо усіх студентів аспірантів
     * ЯКЩО menuOption 3 - то ми викликаємо у studentBachelorRepository метод getAllStudents та після чого виводимо усіх студентів бакалаврів
     * ЯКЩО menuOption 4 - то ми викликаємо у studentAspirantRepository метод getStudentsByForm та після чого виводимо усіх студентів аспірантів за формою
     * ЯКЩО menuOption 5 - то ми викликаємо у studentBachelorRepository метод getStudentsByForm та після чого виводимо усіх студентів бакалаврів за формою
     */
    public void runUi() throws SQLException {
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
