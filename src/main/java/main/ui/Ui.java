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

    public Person inputPersonEmailData() {
        String email = ValidateOwners.validateEmail(scanner);
        return new Person(email);
    }

    public Dog inputDogData() throws SQLException {
        Integer medicalCardId = ValidateAnimals.validateMedicalCardId(scanner, dogRepository.getConnection());
        String name = ValidateAnimals.validateName(scanner);
        Integer age = ValidateAnimals.validateAge(scanner);
        String type = "OVCHARKA";
        return new Dog(name, medicalCardId, age, type);
    }

    public Cat inputCatData() throws SQLException {
        Integer medicalCardId = ValidateAnimals.validateMedicalCardId(scanner, catRepository.getConnection());
        String name = ValidateAnimals.validateName(scanner);
        Integer age = ValidateAnimals.validateAge(scanner);
        Boolean flyingDream = ValidateCats.validateFlyingDream(scanner);
        return new Cat(name, age, medicalCardId, flyingDream);
    }

    public String inputIllData() throws SQLException {
        System.out.println("Enter the Ill");
        String ill = scanner.nextLine();
        return ill;
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
                    Dog dog = inputDogData();
                    dogRepository.addDog(dog);
                    dog.setId(dogRepository.findDogIdByMedicalIdCard(dog, dogRepository.getConnection()));
                    person.setId(personRepository.findPersonIdByEmail(person, personRepository.getConnection()));
                    clinicRepository.registration(new Clinic(person.getId(), dog.getId(), inputIllData()));
                }
                case ADD_CAT -> {
                    Person person = inputPersonEmailData();
                    Cat cat = inputCatData();
                    catRepository.addCat(cat);
                    cat.setId(catRepository.findCatIdByMedicalIdCard(cat, catRepository.getConnection()));
                    person.setId(personRepository.findPersonIdByEmail(person, personRepository.getConnection()));
                    clinicRepository.registration(new Clinic(person.getId(), cat.getId(), inputIllData()));
                }
                case DISCHARGE_DOG -> {
                    Person person = inputPersonEmailData();
                    Dog dog = inputDogData();
                    person.setId(personRepository.findPersonIdByEmail(person, personRepository.getConnection()));
                    dog.setAnimalId(dogRepository.findDogIdByMedicalIdCard(dog, personRepository.getConnection()));
                    clinicRepository.dischargeDog(person, dog, dogRepository.getConnection());
                    dogRepository.dischargeDog(dog, dogRepository.getConnection());
                }
                case DISCHARGE_CAT -> {
                    Person person = inputPersonEmailData();
                    Cat cat = inputCatData();
                    person.setId(personRepository.findPersonIdByEmail(person, personRepository.getConnection()));
                    cat.setAnimalId(catRepository.findCatIdByMedicalIdCard(cat, personRepository.getConnection()));
                    clinicRepository.dischargeCat(person, cat, catRepository.getConnection());
                    catRepository.dischargeCat(cat, catRepository.getConnection());
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
