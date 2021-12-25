package ch.juventus.carrental.service;

import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.presistance.FileDatabase;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CarService {

    private final FileDatabase fileDataBase;

    public CarService(FileDatabase fileDataBase) {
        this.fileDataBase = fileDataBase;
    }

    public String getHelloWorldGreeting() {
        return fileDataBase.loadHelloWorldGreeting();
    }

    public String getAllCars() throws IOException { return fileDataBase.dbAsString();}


    public void postCarToDB(String carString) throws IOException {
        List<Car> cars = fileDataBase.dbAsObject();
        Car newCar = fileDataBase.jsonStringToObjact(carString);
        newCar.setId(fileDataBase.idHeandler());
        cars.add(newCar);
        fileDataBase.saveArrayAsDB(cars);
    }

    public String getCarById(Integer id) throws IOException {
        return fileDataBase.objectToJsonString(fileDataBase.showCarByID(id));
    }

    public void deleteCar(Integer id) throws IOException {
        fileDataBase.saveArrayAsDB(fileDataBase.deleteCarByID(id));
    }

    public void editCar(String carString, Integer id) throws IOException {
        Car oldCar = fileDataBase.showCarByID(id);
        Car newCar = fileDataBase.jsonStringToObjact(carString);

        fileDataBase.deleteCarByID((int) oldCar.getId());
        // new Car Id was memoried in carStrin
        List<Car> cars = fileDataBase.dbAsObject();
        cars.add(newCar);
        fileDataBase.saveArrayAsDB(cars);
    }

    public void rentCar(String rentings, int id) throws IOException {
        /*
        Car rentCar = fileDataBase.showCarByID(id);
        List<Car> cars = fileDataBase.deleteCarByID(id);
        rentCar.addRentInformation(rentings);
        cars.add(rentCar);
        fileDataBase.saveArrayAsDB(cars);

         */
    }
}
