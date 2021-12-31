package ch.juventus.carrental.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Filter {
    private Date startDate;
    private Date endDate;
    private String query;
    private List<Car.Type> types;
    private Car.Transmission transmission;
    private PricePerDay pricePerDay;
    private int[] seats;
    private boolean airCondition;
    private String fuel;


    public Filter(
            @JsonProperty(value = "startDate") Date startDate,
            @JsonProperty(value = "endDate") Date endDate,
            @JsonProperty(value = "query") String query,
            @JsonProperty(value = "types") List<Car.Type> types,
            @JsonProperty(value = "transmission") Car.Transmission transmission,
            @JsonProperty(value = "pricePerDay") PricePerDay pricePerDay,
            @JsonProperty(value = "seats") int[] seats,
            @JsonProperty(value = "airCondition") boolean airCondition,
            @JsonProperty(value = "fuel") String fuel) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.query = query;
        this.types = types;
        this.transmission = transmission;
        this.pricePerDay = pricePerDay;
        this.seats = seats;
        this.airCondition = airCondition;
        this.fuel = fuel;
    }

    /*
    public class PricePerDay{
        public double min;
        public double max;
    }


    public void PricePerDay(
            @JsonProperty(value = "min") double min,
            @JsonProperty(value = "max") double max){
        PricePerDay.min = min;
        PricePerDay.max = max;
    }

     */




    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
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

    public int[] getSeats() {
        return seats;
    }


    public boolean isAirCondition() {
        return airCondition;
    }

    public String getFuel() {
        return fuel;
    }
}
