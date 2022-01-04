package ch.juventus.carrental.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Car car;
    private Object RentInformation;

    @BeforeAll
    public static void setupAll(){
        System.out.println("This is a CarTest");
    }

    @BeforeEach
    public void setup(){
        //creat a car
        car = new Car(1,"Seat", Car.Type.SUV, Car.Transmission.AUTOMATIC,4, 140, true, (List<ch.juventus.carrental.model.RentInformation>) RentInformation);


    }

    @Test
    void testGetId() {
        car.getId();
        System.out.println(car.getId());
    }

    @Test
    void setId() {
    }

    @Test
    void testGetName() {
        car.getName();
        System.out.println(car.getName());
    }

    @Test
    void testGetType() {
        car.getType();
        System.out.println(car.getType());
    }

    @Test
    void testGetTransmission() {
        car.getTransmission();
        System.out.println(car.getTransmission());
    }

    @Test
    void testGetSeats() {
        car.getSeats();
        System.out.println(car.getSeats());
    }

    @Test
    void testGetPricePerDay() {
        car.getPricePerDay();
        System.out.println(car.getPricePerDay());
    }

    @Test
    void testIsAirConditionTrue() {
        car.isAirCondition();
        System.out.println(car.isAirCondition());
    }

    @Test
    void testGetRentInformation() {
        car.getRentInformation();
        System.out.println(car.getRentInformation());
    }

    @Test
    void addRentInformation() {
    }
}