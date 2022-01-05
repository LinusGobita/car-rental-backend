package ch.juventus.carrental.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Car {

    private long id;
    private final String name;
    private final Type type;
    private final Transmission transmission;
    private final int seats;
    private final double pricePerDay;
    private final boolean airCondition;
    private List<RentInformation> rentInformation;


    // Constructor
    public Car(
            @JsonProperty("id") long id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "type", required = true) Type type,
            @JsonProperty(value = "transmission", required = true) Transmission transmission,
            @JsonProperty(value = "seats", required = true) int seats,
            @JsonProperty(value = "pricePerDay", required = true) double pricePerDay,
            @JsonProperty(value = "airCondition", required = true) boolean airCondition,
            @JsonProperty(value = "rentInformation") List<RentInformation> rentInformation) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.transmission = transmission;
        this.seats = seats;
        this.pricePerDay = pricePerDay;
        this.airCondition = airCondition;
        this.rentInformation = rentInformation;

    }



    public enum Type{
        CABRIO,
        LIMOUSINE,
        SUV,
        MINIBUS,
        COUPE,
        ESTATE
    }


    public enum Transmission {
        MANUAL,
        AUTOMATIC;

    }

    public long getId() {
        return id;
    }
    public void setId(long newId) {id = newId;}

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public int getSeats() {
        return seats;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAirCondition() {
        return airCondition;
    }

    public List<RentInformation> getRentInformation() {
        return rentInformation;
    }
    public void addRentInformation(RentInformation newRentInformation) {rentInformation.add(newRentInformation);}
}
