package dat3.cars.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Car {

    @Id
    @GeneratedValue
    private int id;

    private String brand, model;
    private double pricePrDay;
    private int bestDiscount;
}
