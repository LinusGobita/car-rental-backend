package ch.juventus.carrental.presistance;

import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.service.CarService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class FileDatabaseTest {

    private Object RentInformation;
    private FileDatabase fileDatabase;


    @BeforeAll
    public static void setupAll(){
        System.out.println("This is a FileDatabaseTest");
    }

    @BeforeEach
    public void setUp() throws Exception {
        fileDatabase = new FileDatabase();
        String databasePath = "src/test/java/ch/juventus/carrental/presistance/cars.json";
        fileDatabase.init();


    }

    @Test
    void dbAsString() {
    }

    @Test
    void dbAsObject() {


    }

    @Test
    void testShowCarNameWithId4() throws IOException {
        String carname = "BMW i8";

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
        assertEquals(carid, fileDatabase.showCarByID(2).getId(), "car deleted");
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