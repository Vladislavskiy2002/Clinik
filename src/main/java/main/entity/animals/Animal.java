package main.entity.animals;

import lombok.Data;

@Data
public abstract class Animal {
    private Integer id;
    private String name;
    private Integer age;
    private Integer medicalCardId;

    public Animal(String name, Integer age, Integer medicalCardId) {
        this.name = name;
        this.age = age;
        this.medicalCardId = medicalCardId;
    }

    public Animal() {

    }
}
