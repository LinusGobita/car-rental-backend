package ch.juventus.carrental.controller;

import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.model.RentInformation;
import ch.juventus.carrental.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarControllerTest {

    private Object RentInformation;

    @Test
    void allCars() {



    }

    @Test
    void addCar() {

        Car car = new Car(1,"Seat", Car.Type.SUV, Car.Transmission.AUTOMATIC,4, 140, true, (List<ch.juventus.carrental.model.RentInformation>) RentInformation);


    }

    @Test
    void showCar() {
    }

    @Test
    void editCar() {
    }

    @Test
    void deleteCar() {
    }

    @Test
    void rentCar() {
    }

    @Test
    void filterCars() {
    }
}