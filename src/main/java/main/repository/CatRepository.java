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

    public void addCat(Cat cat) throws SQLException {
        Util.addAnimal(cat, connection);
        service.addCat(cat, connection);
    }

    public int findCatIdByMedicalIdCard(Cat cat, Connection connection) throws SQLException {
        return service.findAnimalIdByMedicalIdCard(cat, connection);
    }

    public void dischargeCat(Person person, Cat cat, Connection connection) throws SQLException {
        service.dischargeCat(person, cat, connection);
    }

    public void showCats(Person person, Connection connection) throws SQLException {
        service.showCats(person, connection);
    }
}
