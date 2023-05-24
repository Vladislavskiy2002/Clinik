package main.entity.animals;

import lombok.Data;

@Data
public class Dog extends Animal {
    private Integer id;
    private Integer animalId;
    private String type;

    public Dog(String name, Integer age, Integer medicalCardId, String type) {
        super(name, medicalCardId, age);
        this.type = type;
    }
}
