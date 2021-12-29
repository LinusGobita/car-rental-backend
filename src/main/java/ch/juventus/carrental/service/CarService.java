package ch.juventus.carrental.service;

import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.model.RentInformation;
import ch.juventus.carrental.presistance.Database;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CarService {

    private final Database dataBase;

    public CarService(Database dataBase) {
        this.dataBase = dataBase;
    }


    public String getAllCars() throws IOException { return dataBase.dbAsString();}


    public void postCarToDB(String carString) throws IOException {
        Car newCar = dataBase.jsonStringToObjact(carString);
        newCar.setId(dataBase.idHeandler());
        dataBase.addCar(newCar);
    }

    public String getCarById(Integer id) throws IOException {
        return dataBase.objectToJsonString(dataBase.showCarByID(id));
    }

    public void deleteCar(Integer id) throws IOException {
        dataBase.removeCarByID(id);
    }

    public void editCar(String carString, Integer id) throws IOException {
        Car oldCar = dataBase.showCarByID(id);
        Car newCar = dataBase.jsonStringToObjact(carString);

        dataBase.removeCar(oldCar);
        dataBase.addCar(newCar);
    }

    public void rentCar(RentInformation rentings, int id) throws IOException {


    }
}
