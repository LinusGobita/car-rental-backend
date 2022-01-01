package ch.juventus.carrental.controller;


import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.model.Filter;
import ch.juventus.carrental.model.RentInformation;
import ch.juventus.carrental.service.CarService;
import ch.juventus.carrental.service.FilterEditor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/")
public class CarController {


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
            List<Car> response = carService.getAllCars();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            List<Car> response = carService.getCars(filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    //Fügt ein neues Auto im System hinzu
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @PostMapping("car/")
    public void addCar(@RequestBody String car) throws IOException {
        carService.postCarToDB(car);
        System.out.println("add this car: " + car);
    }

    //Liefert ein Auto mit der gegebenen ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @GetMapping("car/{id}")
    public ResponseEntity<String> showCar(@PathVariable int id) throws IOException {
        String response = carService.getCarById(id);
        System.out.println("show car with id: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Editiert ein bestehendes Auto mit der gegebenen ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @PutMapping("car/{id}")
    public void editCar(@RequestBody String car, @PathVariable Integer id) throws IOException {
        carService.editCar(car, id);
        System.out.println("edit car with id: " + id + car);
    }

    //Löscht ein bestehendes Auto mit der gegebenen ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    @DeleteMapping("car/{id}")
    public void deleteCar(@PathVariable Integer id) throws IOException {
        carService.deleteCar(id);
        System.out.println("delete car with id: " + id);
    }

    //Vermietet ein Auto mit der gegebenen ID
    @CrossOrigin(origins = FRONTEND_ENDPOINT)
    //@PutMapping("car/{id}")
    @PostMapping("car/{id}/rentings")
    public void rentCar(@RequestBody RentInformation rentings, @PathVariable int id) throws IOException {
        System.out.println("rent Car with id = " + id + " on Day " + rentings);
        carService.rentCar(rentings, id);
    }

    //Sucht Autos mit gewissen Filterkriterien
    //Die Liste der passenden Autos soll aufsteigend nach Preis sortiert sein


}
