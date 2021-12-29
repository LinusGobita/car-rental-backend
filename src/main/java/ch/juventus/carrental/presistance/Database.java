package ch.juventus.carrental.presistance;

import ch.juventus.carrental.model.Car;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface Database {

    String dbAsString() throws IOException;
    Car showCarByID(Integer id) throws IOException;
    String objectToJsonString(Car carObject) throws JsonProcessingException;
    Car jsonStringToObjact(String carString) throws JsonProcessingException;
    Integer idHeandler() throws IOException;
    void addCar(Car car);
    List<Car> getCars();
    void removeCar(Car car);
    void removeCarByID(Integer id) throws IOException;

}
