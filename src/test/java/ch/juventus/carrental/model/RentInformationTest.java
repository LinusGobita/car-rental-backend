package ch.juventus.carrental.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentInformationTest {

    RentInformation rentInformation;

    @BeforeAll
    public static void setupAll(){
        System.out.println("This is a RentInformationTest");
    }

    @BeforeEach
    public void setup(){
        rentInformation = new RentInformation();
        String startDate;
        String endDate;
        int totalPrice;


    }

    @Test
    void testGetStartDate(){
        rentInformation.getStartDate();
        System.out.println(rentInformation.getStartDate());

    }

    @Test
    void testSetStartDate(){
        rentInformation.setStartDate("Dezember");
        System.out.println(rentInformation.getStartDate());

    }

    @Test
    void testGetEnd(){
        rentInformation.getEndDate();
        System.out.println(rentInformation.getEndDate());
    }

    @Test
    void testSetEndDate(){
        rentInformation.setEndDate("Januar");
        System.out.println(rentInformation.getEndDate());
    }

    @Test
    void getTotalPrice(){
        rentInformation.getTotalPrice();
        System.out.println(rentInformation.getTotalPrice());
    }

    @Test
    void setTotalPrica(){
        rentInformation.setTotalPrice(100);
        System.out.println(rentInformation.getTotalPrice());
    }



}