package ch.juventus.carrental.controller;


import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.model.Filter;
import ch.juventus.carrental.model.RentInformation;
import ch.juventus.carrental.service.CarService;
import ch.juventus.carrental.service.FilterEditor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class CarController {

    //Logger declaration
    final Logger logger = LoggerFactory.getLogger(CarController.class);

    private static final String FRONTEND_ENDPOINT = "http://localhost:4200";
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @InitBinder
    @SuppressWarnings("unused")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Filter.class, new FilterEditor(new ObjectMapper()));
    }


    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @GetMapping("cars")
    public ResponseEntity<List<Car>> getCars(@RequestParam(required = false) Filter filter) throws IOException {
        if (filter == null){
            System.out.println("Get all Cars");
            logger.info("Get all Cars");
            List<Car> response = carService.getAllCars();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            List<Car> response = carService.getCars(filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    //Adds a new car to the system
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @PostMapping("car/")
    public ResponseEntity<String> addCar(@RequestBody String car) throws IOException {

        //Return a Logger information when car is added
        logger.info("add this car: " + car);
        System.out.println("add this car: " + car);
        carService.postCarToDB(car);
        return new ResponseEntity<>("New car added", HttpStatus.OK);

    }

    //Returns a car with the given ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @GetMapping("car/{id}")
    public ResponseEntity<String> showCar(@PathVariable int id) throws IOException {
        String response = carService.getCarById(id);
        logger.info("show car with id " + id);
        System.out.println("show car with id: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Edits an existing car with the given ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @PutMapping("car/{id}")
    public ResponseEntity<String> editCar(@RequestBody String car, @PathVariable Integer id) throws IOException {
        carService.editCar(car, id);
        logger.info("edit car with id: " + id + car);
        System.out.println("edit car with id: " + id + car);
        return new ResponseEntity<>("Car edited", HttpStatus.OK);
    }

    //Deletes an existing car with the given ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @DeleteMapping("car/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Integer id) throws IOException {
        carService.deleteCar(id);
        logger.info("delete car with id: " + id);
        System.out.println("delete car with id: " + id);
        return new ResponseEntity<>("car deleted", HttpStatus.OK);
    }

    //Rent a car with the given ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    //@PutMapping("car/{id}")
    @PostMapping("car/{id}/rentings")
    public ResponseEntity<String> rentCar(@RequestBody RentInformation rentings, @PathVariable int id) throws IOException {
        logger.info("rent Car with id = " + id + " on Day " + rentings);
        System.out.println("rent Car with id = " + id + " on Day " + rentings);
        carService.rentCar(rentings, id);
        return new ResponseEntity<>("Car rented"+ id +" on Day " + rentings, HttpStatus.OK);
    }

    //Searches for cars with certain filter criteria
    //The list of suitable cars should be sorted in ascending order by price


}
