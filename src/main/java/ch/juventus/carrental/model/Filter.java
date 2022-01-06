package ch.juventus.carrental.model;

import ch.juventus.carrental.service.FilterEditor;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Filter {
    private LocalDate startDate;
    private LocalDate endDate;
    private String query;
    private List<Car.Type> types;
    private Car.Transmission transmission;
    private PricePerDay pricePerDay;
    private List<Integer> seats;
    private boolean airCondition;
    private String fuel;

    @JsonCreator
    public Filter(
            @JsonProperty(value = "startDate") String startDate,
            @JsonProperty(value = "endDate") String endDate,
            @JsonProperty(value = "query") String query,
            @JsonProperty(value = "types") List<Car.Type> types,
            @JsonProperty(value = "transmission") Car.Transmission transmission,
            @JsonProperty(value = "pricePerDay") PricePerDay pricePerDay,
            @JsonProperty(value = "seats") List<Integer> seats,
            @JsonProperty(value = "airCondition") boolean airCondition,
            @JsonProperty(value = "fuel") String fuel) {
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.query = query;
        this.types = types;
        this.transmission = transmission;
        this.pricePerDay = pricePerDay;
        this.seats = seats;
        this.airCondition = airCondition;
        this.fuel = fuel;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getQuery() {
        return query;
    }

    public PricePerDay getPricePerDay(){return pricePerDay;}

    public List<Car.Type> getTypes() {
        return types;
    }

    public Car.Transmission getTransmission() {
        return transmission;
    }

    public List<Integer> getSeats() {
        return seats;
    }


    public boolean isAirCondition() {
        return airCondition;
    }

    public String getFuel() {
        return fuel;
    }
}
