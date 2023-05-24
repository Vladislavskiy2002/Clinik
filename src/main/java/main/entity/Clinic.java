package main.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Clinic {
    private Integer id;
    private Integer userId;
    private Integer animalId;
    private String ill;
    private String date;

    public Clinic(Integer userId, Integer animalId, String ill) {
        this.userId = userId;
        this.animalId = animalId;
        this.ill = ill;
        this.date = LocalDate.now().toString();
    }
}
