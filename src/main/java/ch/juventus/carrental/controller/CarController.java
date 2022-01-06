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

    //if not used
    @InitBinder
    @SuppressWarnings("unused")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Filter.class, new FilterEditor(new ObjectMapper()));
    }

    //returns all cars
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @GetMapping("cars")
    public ResponseEntity<List<Car>> getCars(@RequestParam(required = false) Filter filter) throws IOException {
        if (filter == null){
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
        carService.postCarToDB(car);
        //Return a Logger information when car is added
        logger.info("add this car: " + car);
        return new ResponseEntity<>( HttpStatus.OK);

    }

    //Returns a car with the given ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @GetMapping("car/{id}")
    public ResponseEntity<String> showCar(@PathVariable int id) throws IOException {
        String response = carService.getCarById(id);
        logger.info("show car with id " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Edits an existing car with the given ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @PutMapping("car/{id}")
    public ResponseEntity<String> editCar(@RequestBody String car, @PathVariable Integer id) throws IOException {
        carService.editCar(car, id);
        logger.info("edit car with id: " + id + car);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    //Deletes an existing car with the given ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @DeleteMapping("car/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Integer id) throws IOException {
        carService.deleteCar(id);
        logger.info("delete car with id: " + id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    //Rent a car with the given ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    //@PutMapping("car/{id}")
    @PostMapping("car/{id}/rentings")
    public ResponseEntity<String> rentCar(@RequestBody RentInformation rent, @PathVariable int id) throws IOException {
        carService.rentCar(rent, id);
        logger.info("rent Car with id = " + id + " on Day " + rent.getStartDate() + " to " + rent.getEndDate() );
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
