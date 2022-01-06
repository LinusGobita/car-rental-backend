package ch.juventus.carrental.presistance;


import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.model.Filter;
import ch.juventus.carrental.model.RentInformation;
import ch.juventus.carrental.service.FilterEditor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    private final ObjectMapper objectMapper = new ObjectMapper();
    List<Car> cars = new ArrayList<Car>();


    @PostConstruct
    public void init() throws IOException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //load db first Time
        File file = new File(databasePath);
        cars = objectMapper.readValue(file, new TypeReference<>() {
        });

        //regular storage in db
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
    public String carToJsonString(Car carObject) throws JsonProcessingException {
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
     * ####### Queck the next free id #######
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


    @Override
    public List<Car> getAllCars() {
        return cars;
    }

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
     * ####### Filters #######
     */


    public List<Car> filterCars(Filter filter) throws JsonProcessingException {
        List<Car> filterCars = new ArrayList<Car>(cars);
        FilterEditor.getFilterTable(cars, filter);

        for (Car car : cars) {
            filterDate(filterCars, car, filter);
            filterQuery(filterCars, car, filter);
            filterType(filterCars, car, filter);
            filterTramsmission(filterCars, car, filter);
            filterPrice(filterCars, car, filter);
            filterSeats(filterCars, car, filter);
            filterAircondition(filterCars, car, filter);
        }

        return filterCars;
    }


    //Date
    public List<Car> filterDate(List<Car> filterCars, Car car, Filter filter) {

        for (RentInformation rent : car.getRentInformation()) {
            // filter is bevor rent
            if (filter.getStartDate().isBefore(rent.getStartDate()) && filter.getEndDate().isBefore(rent.getStartDate())) {
                // filter is after rent
            } else if (filter.getStartDate().isAfter(rent.getEndDate()) && filter.getEndDate().isAfter(rent.getEndDate())) {
            } else {
                filterCars.remove(car);
            }
        }
        return filterCars;
    }

    //Query
    public List<Car> filterQuery(List<Car> filterCars, Car car, Filter filter) throws JsonProcessingException {
        if (filter.getQuery() == null) {
        } else if (carToJsonString(car).toLowerCase(Locale.ROOT).contains(filter.getQuery().toLowerCase(Locale.ROOT))) {
        } else {
            filterCars.remove(car);
        }
        return filterCars;
    }

    //Type
    public List<Car> filterType(List<Car> filterCars, Car car, Filter filter) {
        if (filter.getTypes() == null) {
        } else if (filter.getTypes().contains(car.getType())) {
        } else {
            filterCars.remove(car);
        }
        return filterCars;
    }
    //Tramsmission
    public List<Car> filterTramsmission(List<Car> filterCars, Car car, Filter filter) {
        if (filter.getTransmission() == null) {
        } else if (filter.getTransmission() == car.getTransmission()) {
        } else {
            filterCars.remove(car);
        }
        return filterCars;
    }

    //Privce
    public List<Car> filterPrice(List<Car> filterCars, Car car, Filter filter) {
        if (filter.getPricePerDay().min == 0.0) {
        } else if (car.getPricePerDay() >= filter.getPricePerDay().min) {
        } else {
            filterCars.remove(car);
        }
        //0 < 160
        if (filter.getPricePerDay().max == 0.0) {
        } else if (car.getPricePerDay() <= filter.getPricePerDay().max) {
        } else {
            filterCars.remove(car);
        }
        return filterCars;
    }

    //Seats
    public List<Car> filterSeats(List<Car> filterCars, Car car, Filter filter) {
        if (filter.getSeats() == null) {
        } else if (filter.getSeats().contains(car.getSeats())) {
        } else {
            filterCars.remove(car);
        }
        return filterCars;
    }

    //Aircondition
    public List<Car> filterAircondition(List<Car> filterCars, Car car, Filter filter) {
        if (filter.isAirCondition() == car.isAirCondition()) {
        } else {
            filterCars.remove(car);
        }
        return filterCars;
    }

    /**
     * ####### Save in DB #######
     * @throws IOException
     */

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
                if (car.getRentInformation().isEmpty()) {
                    file.write("  \"rentInformation\": [] ");
                } else {
                    file.write("  \"rentInformation\": [");
                    List<RentInformation> rentings = car.getRentInformation();

                    for (RentInformation rent : rentings) {
                        file.write("{\"startDate\": \"" + rent.getStartDate() + "\", \"endDate\": \"" + rent.getEndDate()
                                + "\" ,\"totalPrice\" :\"" + rent.getTotalPrice() + "\"}");
                        if (rent != rentings.get(rentings.size() - 1)) {
                            file.write(",");
                        } else {
                            file.write("]\n");
                        }
                    }

                }


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

}
