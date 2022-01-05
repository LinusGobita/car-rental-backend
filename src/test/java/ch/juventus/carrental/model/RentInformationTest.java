package ch.juventus.carrental.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RentInformationTest {

    RentInformation rentInformation;

    @BeforeAll
    public static void setupAll(){
        System.out.println("This is a RentInformationTest");
    }

    @BeforeEach
    public void setup(){

        LocalDate startDate;
        LocalDate endDate;
        int totalPrice;
    }

    @Test
    void TestGetStartDate() {
    }

    @Test
    void TestGetEndDate() {
    }

    @Test
    void TestGetTotalPrice() {
    }
}