package ch.juventus.carrental.service;

import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.model.RentInformation;
import ch.juventus.carrental.presistance.Database;
import ch.juventus.carrental.presistance.FileDatabase;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CarService {

    private final Database dataBase;

    public CarService(Database dataBase) {
        this.dataBase = dataBase;
    }


    public String getAllCars() throws IOException { return dataBase.dbAsString();}


    public void postCarToDB(String carString) throws IOException {
        List<Car> cars = dataBase.dbAsObject();
        Car newCar = dataBase.jsonStringToObjact(carString);
        newCar.setId(dataBase.idHeandler());
        cars.add(newCar);
        dataBase.saveArrayAsDB(cars);
    }

    public String getCarById(Integer id) throws IOException {
        return dataBase.objectToJsonString(dataBase.showCarByID(id));
    }

    public void deleteCar(Integer id) throws IOException {
        dataBase.saveArrayAsDB(dataBase.deleteCarByID(id));
    }

    public void editCar(String carString, Integer id) throws IOException {
        Car oldCar = dataBase.showCarByID(id);
        Car newCar = dataBase.jsonStringToObjact(carString);

        dataBase.deleteCarByID((int) oldCar.getId());
        // new Car Id was memoried in carStrin
        List<Car> cars = dataBase.dbAsObject();
        cars.add(newCar);
        dataBase.saveArrayAsDB(cars);
    }

    public void rentCar(RentInformation rentings, int id) throws IOException {

        Car rentCar = dataBase.showCarByID(id);
        rentCar.addRentInformation(rentings);
        List<Car> cars = dataBase.deleteCarByID(id);
        cars.add(rentCar);
        System.out.println("added rentings");
        dataBase.saveArrayAsDB(cars);

    }
}
