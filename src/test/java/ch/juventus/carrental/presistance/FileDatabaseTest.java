package ch.juventus.carrental.presistance;

import ch.juventus.carrental.model.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileDatabaseTest {

    private FileDatabase fileDatabase;
    private Object RentInformation;

    @BeforeAll
    public static void setupAll(){
        System.out.println("This is a FileDatabaseTest");
    }

    @BeforeEach
    public void setUp() throws Exception {
        List<Car> testCars = new ArrayList<Car>();
        Car car1 = new Car(1,"Seat", Car.Type.SUV, Car.Transmission.AUTOMATIC,4, 140, true, (List<ch.juventus.carrental.model.RentInformation>) RentInformation);
        Car car2 = new Car(1,"Seat", Car.Type.SUV, Car.Transmission.AUTOMATIC,4, 140, true, (List<ch.juventus.carrental.model.RentInformation>) RentInformation);
        Car car3 = new Car(1,"Seat", Car.Type.SUV, Car.Transmission.AUTOMATIC,4, 140, true, (List<ch.juventus.carrental.model.RentInformation>) RentInformation);

        testCars.add(car1);
        testCars.add(car2);
        testCars.add(car3);


    }

    @Test
    void dbAsString() {
    }

    @Test
    void dbAsObject() {


    }

    @Test
    void testShowCarNameWithId4() throws IOException {
        String carname = "BMW i8 elektro";

        assertEquals(carname, fileDatabase.showCarByID(4).getName(), "Calculation result wrong");

    }
    @Test
    void testShowCarNameWithId3() throws IOException {
        String carname = "Porsche 911";

        assertEquals(carname, fileDatabase.showCarByID(3).getName(), "Calculation result wrong");
    }

    @Test
    void testDeleteCarByID() throws IOException {
        int carid = 2;
        assertEquals(carid, fileDatabase.showCarByID(2).getId(), "car deletet");

    }

    @Test
    void testObjectToJsonString() {
    }

    @Test
    void testJsonStringToObjact() {
    }

    @Test
    void testIdHeandler() {

    }

    @Test
    void testSaveArrayAsDB() {
    }

}