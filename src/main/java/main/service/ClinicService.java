package main.service;

import main.entity.Clinic;
import main.entity.Person;
import main.entity.animals.Cat;
import main.entity.animals.Dog;
import main.validation.ValidateAnimals;
import main.validation.ValidateDogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClinicService {
    /**
     * Метод registrate - метод класу ClinicService який додає данні (owner_id, animal_id, ill, date) до таблиці clinic
     */
    public void registrate(Clinic clinic, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into clinic (owner_id, animal_id, ill, date) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, clinic.getUserId());
            preparedStatement.setInt(2, clinic.getAnimalId());
            preparedStatement.setString(3, clinic.getIll());
            preparedStatement.setString(4, clinic.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод dischargeDog - метод класу ClinicService який виписує собаку з лікарні (видаляє з усіх таблиць (clinic,dogs,animal) у яких є її id)
     */
    public void dischargeDog(Person person, Dog dog, Connection connection) throws SQLException {
        if (ValidateDogs.validateIfCurrentDogWithCurrentOwnerExist(person,dog.getMedicalCardId(),connection)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM clinic WHERE owner_id = ? and animal_id = ?");
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setInt(2, dog.getAnimalId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM dogs WHERE animal_id = ?");
            preparedStatement.setInt(1, dog.getAnimalId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM animal WHERE id = ?");
            preparedStatement.setInt(1, dog.getAnimalId());
            preparedStatement.executeUpdate();

            System.out.println("THE DOG HAS BEEN DISCHARGED");
        }
    }
    /**
     * Метод dischargeCat - метод класу ClinicService який виписує кота з лікарні (видаляє з усіх таблиць (clinic,cats,animal) у яких є її id)
     */
    public void dischargeCat(Person person, Cat cat, Connection connection) throws SQLException {
        if (ValidateAnimals.validateIfCurrentCatExist(cat.getMedicalCardId(), connection)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM clinic WHERE owner_id = ? and animal_id = ?");
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setInt(2, cat.getAnimalId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM cats WHERE animal_id = ?");
            preparedStatement.setInt(1, cat.getAnimalId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM animal WHERE id = ?");
            preparedStatement.setInt(1, cat.getAnimalId());
            preparedStatement.executeUpdate();
        }
    }
}
