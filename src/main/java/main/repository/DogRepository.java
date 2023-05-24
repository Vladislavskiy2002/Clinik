package main.repository;

import lombok.Data;
import main.entity.Person;
import main.entity.animals.Dog;
import main.service.DogService;
import main.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

@Data
public class DogRepository {
    private Connection connection;
    private DogService service;

    public DogRepository(Connection connection) {
        this.connection = connection;
        this.service = new DogService();
    }

    public void addDog(Dog dog) throws SQLException {
        Util.addAnimal(dog, connection);
        service.addDog(dog, connection);
    }

    public int findDogIdByMedicalIdCard(Dog dog, Connection connection) throws SQLException {
        return service.findAnimalIdByMedicalIdCard(dog, connection);
    }

    public void dischargeDog(Dog dog, Connection connection) throws SQLException {
        service.dischargeDog(dog, connection);
    }

    public void showDogs(Person person, Connection connection) throws SQLException {
        service.showDogs(person, connection);
    }
}
