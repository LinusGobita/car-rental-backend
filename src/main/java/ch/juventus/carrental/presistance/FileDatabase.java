package ch.juventus.carrental.presistance;


import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.model.Filter;
import ch.juventus.carrental.model.RentInformation;
import ch.juventus.carrental.service.FilterEditor;
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
        cars = objectMapper.readValue(dbAsString, new TypeReference<List<Car>>() {
        });


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

    /**
     * Queck the next free id
     */
    @Override
    public Integer idHeandler() throws IOException {
        Set<Integer> ids = new TreeSet<Integer>();

        for (Car car : cars) {
            ids.add((int) car.getId());
        }
        int i = ((TreeSet<Integer>) ids).last() + 1;
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
                file.write("  \"airCondition\": " + car.isAirCondition() + ",\n");
                file.write("  \"rentInformation\": " + rentinformationAsString(car.getRentInformation()) + "\n");

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

    @Override
    public List<Car> getAllCars() {return cars;}

    @Override
    public void addCar(Car car) {
        cars.add(car);
    }
    @Override
    public void addRentInformationToCar(RentInformation rentings, int id) throws IOException {
        Car rentetCar = showCarByID(id);
        rentetCar.addRentInformation(rentings);
    }
    @Override
    public void removeCar(Car car) {
        cars.remove(car);
    }

    @Override
    public void removeCarByID(Integer id) throws IOException {
        cars.remove(showCarByID(id));
    }

    /**
     * Filters
     */
    //Date
    public void filterDate(){

    }
    //Query
    public List<Car> filterQuery(List<Car> filterCars, Car car, Filter filter) throws JsonProcessingException {
        if(filter.getQuery() == null){
        }else if(objectToJsonString(car).toLowerCase(Locale.ROOT).contains(filter.getQuery().toLowerCase(Locale.ROOT))){
        }else{
            filterCars.remove(car);
        }
        return filterCars;
    }

    public List<Car> filterType(List<Car> filterCars, Car car, Filter filter){
        if(filter.getTypes() == null ){
        }else if(filter.getTypes().contains(car.getType())) {
        } else {
            filterCars.remove(car);
        }
        return filterCars;
    }
    public List<Car> filterTramsmission(List<Car> filterCars, Car car, Filter filter){
        if(filter.getTransmission() == null ){
        }else if(filter.getTransmission() == car.getTransmission()){
        } else {
            filterCars.remove(car);
        }
        return filterCars;
    }
  public List<Car> filterPrice(List<Car> filterCars, Car car, Filter filter){
      if(filter.getPricePerDay().min == 0.0){
      } else if (car.getPricePerDay() >= filter.getPricePerDay().min) {
      } else {
          filterCars.remove(car);
      }
      //0 < 160
      if(filter.getPricePerDay().max == 0.0){
      } else if (car.getPricePerDay() <= filter.getPricePerDay().max) {
      } else {
          filterCars.remove(car);
      }
      return filterCars;
    }

    public List<Car> filterSeats(List<Car> filterCars, Car car, Filter filter) {
        if(filter.getSeats() == null){
        } else if (filter.getSeats().contains(car.getSeats())) {
        } else {
            filterCars.remove(car); }
        return filterCars;
    }
     public List<Car> filterAircondition(List<Car> filterCars, Car car, Filter filter) {
         if (filter.isAirCondition() == car.isAirCondition()) {
         } else {
             filterCars.remove(car);
         }
        return filterCars;
    }







    public List<Car> filterCars(Filter filter) throws JsonProcessingException {
        List<Car> filterCars = new ArrayList<Car>(cars);
        FilterEditor.getFilterTable(cars, filter);

        for(Car car : cars) {
            filterQuery(filterCars, car, filter);
            filterType(filterCars, car, filter);
            filterTramsmission(filterCars, car, filter);
            filterPrice(filterCars, car, filter);
            filterSeats(filterCars, car, filter);
            filterAircondition(filterCars, car, filter);
        }

        return filterCars;
    }

}
