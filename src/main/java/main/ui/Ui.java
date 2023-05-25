package main.ui;

import main.entity.Clinic;
import main.entity.Person;
import main.entity.animals.Cat;
import main.entity.animals.Dog;
import main.repository.CatRepository;
import main.repository.ClinicRepository;
import main.repository.DogRepository;
import main.repository.PersonRepository;
import main.validation.ValidateAnimals;
import main.validation.ValidateCats;
import main.validation.ValidateDogs;
import main.validation.ValidateOwners;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static main.util.MyConstants.*;

public class Ui {
    private final Scanner scanner;
    private final PersonRepository personRepository;
    private final DogRepository dogRepository;
    private final CatRepository catRepository;
    private final ClinicRepository clinicRepository;

    public Ui(Connection connection) {
        personRepository = new PersonRepository(connection);
        dogRepository = new DogRepository(connection);
        clinicRepository = new ClinicRepository(connection);
        catRepository = new CatRepository(connection);
        scanner = new Scanner(System.in);
    }

    public int getMenuOption() {
        System.out.println("""
                1. Create owner
                2. Add dog to clinic
                3. Add cat to clinic
                4. Discharge the dog from clinic
                5. Discharge the cat from clinic
                6. Show Owner's dogs
                7. Show Owner's cats
                ---------------
                0. Exit
                """);
        Integer res = ValidateAnimals.validateRes(scanner);
        return res;
    }

    public Person inputPersonData() throws SQLException {
        String name = ValidateAnimals.validateName(scanner);
        String surname = ValidateOwners.validateSurname(scanner);
        String email;
        do {
            email = ValidateOwners.validateEmail(scanner);
        } while (ValidateOwners.validateIfCurrentOwnerExist(email, dogRepository.getConnection()));
        return new Person(name, surname, email);
    }

    public Person inputPersonEmailData() throws SQLException {
        String email;
        do {
            email = ValidateOwners.validateEmail(scanner);
        } while (ValidateOwners.validateIfCurrentOwnerIsNotExist(email, dogRepository.getConnection()));
        return new Person(email);
    }

    public Dog inputAddDogData() throws SQLException {
        Integer medicalCardId = ValidateAnimals.validateOnExistMedicalCardId(scanner, dogRepository.getConnection());
        String name = ValidateAnimals.validateName(scanner);
        Integer age = ValidateAnimals.validateAge(scanner);
        String type = ValidateDogs.validateType(scanner);
        return new Dog(name, medicalCardId, age, type);
    }

    public Cat inputAddCatData() throws SQLException {
        Integer medicalCardId = ValidateAnimals.validateOnExistMedicalCardId(scanner, catRepository.getConnection());
        String name = ValidateAnimals.validateName(scanner);
        Integer age = ValidateAnimals.validateAge(scanner);
        Boolean flyingDream = ValidateCats.validateFlyingDream(scanner);
        return new Cat(name, age, medicalCardId, flyingDream);
    }

    public Integer inputMedicalCardIdData() throws SQLException {
        return ValidateAnimals.validateMedicalCardId(scanner, personRepository.getConnection());
    }

    public String inputIllData() {
        return ValidateAnimals.validateIll(scanner);
    }

    public void runUi() throws SQLException {
        int menuOption;
        while ((menuOption = getMenuOption()) != 0) {
            switch (menuOption) {
                case ADD_PERSON -> {
                    Person person = inputPersonData();
                    personRepository.addPerson(person);
                }
                case ADD_DOG -> {
                    Person person = inputPersonEmailData();
                    Dog dog = inputAddDogData();
                    dogRepository.addDog(dog);
                    dog.setId(dogRepository.findDogIdByMedicalIdCard(dog, dogRepository.getConnection()));
                    person.setId(personRepository.findPersonIdByEmail(person, personRepository.getConnection()));
                    clinicRepository.registration(new Clinic(person.getId(), dog.getId(), inputIllData()));
                }
                case ADD_CAT -> {
                    Person person = inputPersonEmailData();
                    Cat cat = inputAddCatData();
                    catRepository.addCat(cat);
                    cat.setId(catRepository.findCatIdByMedicalIdCard(cat, catRepository.getConnection()));
                    person.setId(personRepository.findPersonIdByEmail(person, personRepository.getConnection()));
                    clinicRepository.registration(new Clinic(person.getId(), cat.getId(), inputIllData()));
                }
                case DISCHARGE_DOG -> {
                    Person person = inputPersonEmailData();
                    Dog dog = new Dog();
                    dog.setMedicalCardId(inputMedicalCardIdData());
                    person.setId(personRepository.findPersonIdByEmail(person, personRepository.getConnection()));
                    dog.setAnimalId(dogRepository.findDogIdByMedicalIdCard(dog, personRepository.getConnection()));
                    clinicRepository.dischargeDog(person, dog, dogRepository.getConnection());
                }
                case DISCHARGE_CAT -> {
                    Person person = inputPersonEmailData();
                    Cat cat = new Cat();
                    cat.setMedicalCardId(inputMedicalCardIdData());
                    person.setId(personRepository.findPersonIdByEmail(person, personRepository.getConnection()));
                    cat.setAnimalId(catRepository.findCatIdByMedicalIdCard(cat, personRepository.getConnection()));
                    clinicRepository.dischargeCat(person, cat, catRepository.getConnection());
                }
                case SHOW_DOGS -> {
                    Person person = inputPersonEmailData();
                    person.setId(personRepository.findPersonIdByEmail(person, personRepository.getConnection()));
                    dogRepository.showDogs(person, dogRepository.getConnection());
                }
                case SHOW_CATS -> {
                    Person person = inputPersonEmailData();
                    person.setId(personRepository.findPersonIdByEmail(person, personRepository.getConnection()));
                    catRepository.showCats(person, catRepository.getConnection());
                }
            }
        }
    }
}
