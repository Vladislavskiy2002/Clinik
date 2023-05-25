package main.repository;

import lombok.Data;
import main.entity.Person;
import main.service.PersonService;

import java.sql.Connection;
import java.sql.SQLException;

@Data
public class PersonRepository {
    private Connection connection;
    private PersonService service;

    public PersonRepository(Connection connection) {
        this.connection = connection;
        this.service = new PersonService();
    }
    /**
     * Метод addPerson - метод класу PersonRepository який викликає service.addPerson
     */
    public void addPerson(Person person) throws SQLException {
        service.addPerson(connection, person);
    }
    /**
     * Метод findPersonIdByEmail - метод класу PersonRepository який викликає service.findPersonIdByEmail
     */
    public int findPersonIdByEmail(Person person, Connection connection) throws SQLException {
        return service.findPersonIdByEmail(person, connection);
    }
}
