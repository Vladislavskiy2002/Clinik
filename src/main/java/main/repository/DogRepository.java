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
    /**
     * Метод addDog - метод класу DogRepository який додає Собак
     * У цьому методі ми викликаємо метод Util.addAnimal для передачі даних собакі в таблицю animal та викликаємо метод service.addDog для передачі даних собакі в таблицю dogs
     */
    public void addDog(Dog dog) throws SQLException {
        Util.addAnimal(dog, connection);
        service.addDog(dog, connection);
    }
    /**
     * Метод findDogIdByMedicalIdCard - метод класу DogRepository який викликає метод findAnimalIdByMedicalIdCard класу DogService
     */
    public int findDogIdByMedicalIdCard(Dog dog, Connection connection) throws SQLException {
        return service.findAnimalIdByMedicalIdCard(dog, connection);
    }

    /**
     * Метод showDogs - метод класу DogRepository який викликає service.showDogs
     */
    public void showDogs(Person person, Connection connection) throws SQLException {
        service.showDogs(person, connection);
    }
}
