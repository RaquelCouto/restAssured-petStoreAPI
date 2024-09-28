package com.exemplo.restassured.models;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import lombok.Data;

@Data
public class Order {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public String generateFutureDate(int daysInFuture) {
        Date futureDate = new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(daysInFuture));
        ZonedDateTime zonedDateTime = futureDate.toInstant().atZone(ZoneOffset.UTC);
        return zonedDateTime.format(DateTimeFormatter.ISO_INSTANT);
    }

    public static Order createOrder(Faker faker) {
        Order newOrder = new Order();
        newOrder.setId(faker.number().randomDigitNotZero());
        newOrder.setPetId(faker.number().randomDigitNotZero());
        newOrder.setQuantity(faker.number().numberBetween(1, 10));
        newOrder.setShipDate(newOrder.generateFutureDate(10));
        newOrder.setStatus("approved");
        newOrder.setComplete(faker.random().nextBoolean());
        return newOrder;
    }
}




