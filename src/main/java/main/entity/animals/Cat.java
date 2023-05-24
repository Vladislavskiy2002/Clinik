package main.entity.animals;

import lombok.Data;

@Data
public class Cat extends Animal {
    private Integer id;
    private Integer animalId;
    private Boolean flyingDream;

    public Cat(String name, Integer age, Integer medicalCardId, Boolean flyingDream) {
        super(name, age, medicalCardId);
        this.flyingDream = flyingDream;
    }

    public Cat(Integer id, String name, Integer age, Integer medicalCardId, Boolean flyingDream) {
        super(name, age, medicalCardId);
        this.flyingDream = flyingDream;
    }

    public Cat() {

    }
}
