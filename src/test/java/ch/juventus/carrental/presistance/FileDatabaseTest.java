package ch.juventus.carrental.presistance;

import ch.juventus.carrental.model.Car;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileDatabaseTest {

    private FileDatabase fileDatabase;

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
        String carname = "BMW i8 elektro";

        assertEquals(carname, fileDatabase.showCarByID(4).getName(), "Calculation result wrong");

    }
    @Test
    void testShowCarNameWithId3() throws IOException {
        String carname = "Porsche 911";

        assertEquals(carname, fileDatabase.showCarByID(3).getName(), "Calculation result wrong");
    }

    @Test
    void deleteCarByID() {
    }

    @Test
    void objectToJsonString() {
    }

    @Test
    void jsonStringToObjact() {
    }

    @Test
    void idHeandler() {
    }

    @Test
    void saveArrayAsDB() {
    }
}