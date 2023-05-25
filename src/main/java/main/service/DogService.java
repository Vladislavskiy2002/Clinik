package main.service;

import main.entity.Person;
import main.entity.animals.Dog;
import main.validation.ValidateDogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DogService extends AnimalService {
    public void addDog(Dog dog, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id from animal where medical_id_card = ?");
        preparedStatement.setInt(1, dog.getMedicalCardId());
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        preparedStatement = connection.prepareStatement("insert into dogs(animal_id, type) VALUES (?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, dog.getType());
        preparedStatement.executeUpdate();
    }

    public void dischargeDog(Person person, Dog dog, Connection connection) throws SQLException {
        if (ValidateDogs.validateIfCurrentDogExist(dog.getMedicalCardId(), connection) && ValidateDogs.validateIfCurrentDogWithCurrentOwnerExist(person,dog.getMedicalCardId(),connection)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM dogs WHERE animal_id = ?");
            preparedStatement.setInt(1, dog.getAnimalId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM animal WHERE id = ?");
            preparedStatement.setInt(1, dog.getAnimalId());
            preparedStatement.executeUpdate();

            System.out.println("THE DOG HAS BEEN DISCHARGED");
        }
    }

    public void showDogs(Person person, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from clinic join dogs d on clinic.animal_id = d.animal_id join animal a on clinic.animal_id = a.id where owner_id = ?");
        preparedStatement.setInt(1, person.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Integer medicalIdCard = resultSet.getInt("medical_id_card");
            String date = resultSet.getString("date");
            String name = resultSet.getString("name");
            Integer age = resultSet.getInt("age");
            String type = resultSet.getString("type");
            String ill = resultSet.getString("ill");

            System.out.println("--------------------------------------");
            System.out.println("\tDog's information");
            System.out.println("\tMedical Id Card: " + medicalIdCard);
            System.out.println("\tName: " + name);
            System.out.println("\tAge: " + age);
            System.out.println("\tBreed: " + type);
            System.out.println("\tDate of registration: " + date);
            System.out.println("\tIll: " + ill);
        }
    }
}
