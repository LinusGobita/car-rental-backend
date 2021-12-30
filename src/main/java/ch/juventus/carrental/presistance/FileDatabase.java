package ch.juventus.carrental.presistance;


import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.model.RentInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


@Repository
public class FileDatabase implements Database {

    private String databasePath = "src/main/java/ch/juventus/carrental/presistance/cars.json";
    List<Car> cars = new ArrayList<Car>();


    @PostConstruct
    public void init() throws IOException {
        //load db first Time
        File file = new File(databasePath);
        String dbAsString = new String(Files.readAllBytes(Paths.get(file.toURI())));
        ObjectMapper objectMapper = new ObjectMapper();
        cars = objectMapper.readValue(dbAsString, new TypeReference<List<Car>>() {});


        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    saveArrayAsDB();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 2000, 10000);
    }

    //Muss noch von cars genommen werden
    public String dbAsString() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson = objectMapper.writeValueAsString(cars);
        return arrayToJson;
    }

    public String rentinformationAsString(List<RentInformation> rentings) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson = objectMapper.writeValueAsString(rentings);
        return arrayToJson;
    }

    @Override
    public Car showCarByID(Integer id) throws IOException {

        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        System.out.println("Felher in FileDatabase Show ID");
        return null;
    }


    @Override
    public String objectToJsonString(Car carObject) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String carInString = objectMapper.writeValueAsString(carObject);
        return carInString;
    }

    @Override
    public Car jsonStringToObjact(String carString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = objectMapper.readValue(carString, Car.class);
        return car;
    }

    @Override
    public Integer idHeandler() throws IOException {
        Set<Integer> ids = new TreeSet<Integer>();

        for (Car car : cars) {
            ids.add((int) car.getId());
        }
        int i = ((TreeSet<Integer>) ids).last() + 1;
        System.out.println("next id" + i);
        return i;
    }

    public void saveArrayAsDB() throws IOException {
        FileWriter file = new FileWriter(databasePath);
        try {
            file.write("[\n");
            for (Car car : cars) {


                file.write(" {\n");
                file.write("  \"id\": " + car.getId() + ",\n");
                file.write("  \"name\": \"" + car.getName() + "\",\n");
                file.write("  \"type\": \"" + car.getType() + "\",\n");
                file.write("  \"transmission\": \"" + car.getTransmission() + "\",\n");
                file.write("  \"seats\": " + car.getSeats() + ",");
                file.write("  \"pricePerDay\": " + car.getPricePerDay() + ",\n");
                file.write("  \"airCondition\": " +  car.isAirCondition() + ",\n");
                file.write("  \"rentInformation\": "+ rentinformationAsString(car.getRentInformation()) +"\n");

                if (car == cars.get(cars.size() - 1)) {
                    file.write(" }\n");
                } else {
                    file.write(" },\n");
                }
            }
            file.write("]\n");
        } catch (IOException e) {

        } finally {
            file.flush();
            file.close();
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addRentInformationToCar(RentInformation rentings, int id) throws IOException {
        Car rentetCar = showCarByID(id);
        rentetCar.addRentInformation(rentings);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    @Override
    public void removeCarByID(Integer id) throws IOException {
        cars.remove(showCarByID(id));
    }


}
