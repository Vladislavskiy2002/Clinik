package main.repository;

import lombok.Data;
import main.entity.Person;
import main.entity.animals.Cat;
import main.service.CatService;
import main.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

@Data
public class CatRepository {
    private Connection connection;
    private CatService service;

    public CatRepository(Connection connection) {
        this.connection = connection;
        this.service = new CatService();
    }

    /**
     * Метод addCat - метод класу CatRepository який додає Котів
     * У цьому методі ми викликаємо метод Util.addAnimal для передачі даних кота в таблицю animal та викликаємо метод service.addCat для передачі даних кота в таблицю cats
     */
    public void addCat(Cat cat) throws SQLException {
        Util.addAnimal(cat, connection);
        service.addCat(cat, connection);
    }
    /**
     * Метод findCatIdByMedicalIdCard - метод класу CatRepository який викликає метод findAnimalIdByMedicalIdCard класу CatService
     */
    public int findCatIdByMedicalIdCard(Cat cat, Connection connection) throws SQLException {
        return service.findAnimalIdByMedicalIdCard(cat, connection);
    }


    /**
     * Метод showCats - метод класу CatRepository який викликає service.showCats
     */
    public void showCats(Person person, Connection connection) throws SQLException {
        service.showCats(person, connection);
    }
}
