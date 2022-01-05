package ch.juventus.carrental.presistance;

import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.model.Filter;
import ch.juventus.carrental.model.RentInformation;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface Database {

    Car showCarByID(Integer id) throws IOException;
    String carToJsonString(Car carObject) throws JsonProcessingException;
    Car jsonStringToObjact(String carString) throws JsonProcessingException;
    Integer idHeandler() throws IOException;
    void addCar(Car car);
    List<Car> filterCars(Filter filter) throws JsonProcessingException;
    List<Car> getAllCars();
    void removeCar(Car car);
    void removeCarByID(Integer id) throws IOException;
    void addRentInformationToCar(RentInformation rentings, int id) throws IOException;

}
