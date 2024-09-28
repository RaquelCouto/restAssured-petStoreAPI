package com.exemplo.restassured.models;

import java.util.ArrayList;
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

    // Create new pet with random data
    public static Pet createPet(String status, Faker faker) {
        Pet pet = new Pet();
        pet.setId(faker.number().randomDigitNotZero());
        pet.setName(faker.animal().name());
        pet.setStatus(status);
        pet.setTags(createTags(faker.number().numberBetween(1, 10)));
        pet.setPhotoUrls(createUrls(faker.number().numberBetween(1, 5), faker));

        Category category = new Category();
        category.setId(faker.number().randomDigitNotZero());
        category.setName(faker.animal().name());
        pet.setCategory(category);

        return pet;
    }

    public static List<Tag> createTags(int numberOfTags) {
        List<Tag> tags = new ArrayList<>();

        for (int i = 0; i < numberOfTags; i++) {
            Tag tag = new Tag();
            tag.setId(i); 
            tag.setName("tag" + i);

            tags.add(tag);
        }
        return tags;
    }

    public static List<String> createUrls(int numberOfUrls, Faker faker) {
        List<String> urls = new ArrayList<>();

        for (int i = 0; i < numberOfUrls; i++) {
            String url = "https://" + faker.internet().domainName() + "/" + faker.lorem().word();
            urls.add(url);
        }

        return urls;
    }

}
