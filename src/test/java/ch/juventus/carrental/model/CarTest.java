package ch.juventus.carrental.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        assertEquals(1, car.getId(),"this isn't the right ID ");
        System.out.println(car.getId());
    }



    @Test
    void testSetId() {
        car.setId(8);
        assertEquals(8, car.getId(),"didnt get the right setter ID ");
        System.out.println(car.getId());
    }

    @Test
    void testGetName() {
        car.getName();
        assertEquals("Seat", car.getName(),"this isn't the right name ");
        System.out.println(car.getName());
    }

    @Test
    void testGetType() {
        car.getType();
        assertEquals(Car.Type.SUV, car.getType(),"this isn't the right type ");
        System.out.println(car.getType());
    }

    @Test
    void testGetTransmission() {
        car.getTransmission();
        assertEquals(Car.Transmission.AUTOMATIC, car.getTransmission(),"this isn't the transmission");
        System.out.println(car.getTransmission());
    }

    @Test
    void testGetSeats() {
        car.getSeats();
        assertEquals(4, car.getSeats(),"this isn't the right seat number ");
        System.out.println(car.getSeats());
    }

    @Test
    void testGetPricePerDay() {
        car.getPricePerDay();
        assertEquals(140, car.getPricePerDay(),"this isn't the right price per day ");
        System.out.println(car.getPricePerDay());
    }

    @Test
    void testIsAirCondition() {
        car.isAirCondition();
        assertTrue(car.isAirCondition(), "Air condition is not true ");
        System.out.println(car.isAirCondition());
    }

    @Test
    void testGetRentInformation() {
        car.getRentInformation();
        assertEquals(RentInformation, car.getRentInformation(),"this isn't the right rent information ");
        System.out.println(car.getRentInformation());
    }

    @Test
    void testAddRentInformation() {
        //don't know how to test
        //car.addRentInformation();
        System.out.println(car.getRentInformation());
    }
}