package ch.juventus.carrental.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;



public class RentInformation {

    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;

    @JsonCreator
    public RentInformation(
            @JsonProperty(value = "startDate") LocalDate startDate,
            @JsonProperty(value = "endDate") LocalDate endDate,
            @JsonProperty(value = "totalPrice") double totalPrice) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }


}
