package com.exemplo.restassured.models;

import java.util.List;
import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class Pet {
    private int id;
    private String name;
    private Category category;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    // Método para criar um novo Pet com dados aleatórios
    public static Pet createPet(String status, Faker faker) {
        Pet pet = new Pet();
        pet.setId(faker.number().randomDigitNotZero());
        pet.setName(faker.animal().name());
        pet.setStatus(status);

        Category category = new Category();
        category.setId(faker.number().randomDigitNotZero());
        category.setName(faker.animal().name());
        pet.setCategory(category);

        return pet;
    }
}
