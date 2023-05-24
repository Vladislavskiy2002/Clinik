package main.repository;

import main.entity.Clinic;
import main.entity.Person;
import main.entity.animals.Cat;
import main.entity.animals.Dog;
import main.service.ClinicService;

import java.sql.Connection;
import java.sql.SQLException;

public class ClinicRepository {
    private final Connection connection;
    private final ClinicService service;

    public ClinicRepository(Connection connection) {
        this.connection = connection;
        this.service = new ClinicService();
    }

    public void registration(Clinic clinic) throws SQLException {
        service.registrate(clinic, connection);
    }

    public void dischargeDog(Person person, Dog dog, Connection connection) throws SQLException {
        service.dischargeDog(person, dog, connection);
    }

    public void dischargeCat(Person person, Cat cat, Connection connection) throws SQLException {
        service.dischargeCat(person, cat, connection);
    }
}
