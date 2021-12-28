package ch.juventus.carrental.presistance;

import ch.juventus.carrental.model.Car;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface Database {

    String dbAsString() throws IOException;
    List<Car> dbAsObject() throws IOException;
    Car showCarByID(Integer id) throws IOException;
    List<Car> deleteCarByID(Integer id) throws IOException;
    String objectToJsonString(Car carObject) throws JsonProcessingException;
    Car jsonStringToObjact(String carString) throws JsonProcessingException;
    Integer idHeandler() throws IOException;
    void saveArrayAsDB(List<Car> cars) throws IOException;

}
