package com.exemplo.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exemplo.restassured.models.Order;
import com.exemplo.restassured.models.Pet;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PetStoreApiTest {

    private Faker faker;
    private Gson gson;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        faker = new Faker();
        gson = new Gson();
    }

    @Test
    public void registerNewPetOrderWithSuccessTest() {
        
        Order newOrder = Order.createOrder(faker);
        String newOrderJson = gson.toJson(newOrder);

        given()
            .contentType(ContentType.JSON)
            .body(newOrderJson)
        .when()
            .post("/store/order")
        .then()
            .log().all()
            .assertThat()
            .statusCode(200)
            .body("id", equalTo(newOrder.getId()))
            .body("petId", equalTo(newOrder.getPetId()))
            .body("quantity", equalTo(newOrder.getQuantity()))
            .body("status", equalTo(newOrder.getStatus()))
            .body("complete", equalTo(newOrder.isComplete()));
    }

    @Test
    public void updateExitingPetTest() {
        
        Pet pet = Pet.createPet("available", faker);
        String petJson = gson.toJson(pet);

        // Create a new pet
        given()
            .contentType(ContentType.JSON)
            .body(petJson)
        .when()
            .post("/pet")
        .then()
            .assertThat()
            .statusCode(200);

        // Update the pet's status
        pet.setStatus("available");
        petJson = gson.toJson(pet);

        given()
            .contentType(ContentType.JSON)
            .body(petJson)
        .when()
            .put("/pet")
        .then()
            .log().all()
            .assertThat()
            .statusCode(200);

        // Clean up: delete the pet as a good test practice
        given()
        .when()
            .delete("/pet/" + pet.getId())
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void searchForPetsWithPendingStatusTest() {
        given()
            .queryParam("status", "pending")
        .when()
            .get("/pet/findByStatus")
        .then()
            .assertThat()
            .statusCode(200)
            .body("status", everyItem(equalTo("pending")));
    }

    @Test
    public void searchForAnInexistentPetNotFoundTest() {
        long invalidPetId = 100000000L + faker.number().randomNumber(9, true);

        given()
        .when()
            .get("/pet/" + invalidPetId)
        .then()
            .log().all()
            .assertThat()
            .statusCode(404)
            .body("message", equalTo("Pet not found"));
    }
}
