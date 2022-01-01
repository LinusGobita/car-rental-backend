package ch.juventus.carrental.service;

import ch.juventus.carrental.model.Car;
import ch.juventus.carrental.model.Filter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class FilterEditor extends PropertyEditorSupport {
    private final ObjectMapper objectMapper;
    public FilterEditor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }



    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text.isEmpty()) {
            setValue(null);
        } else {
            Filter filter;
            try {
                filter = objectMapper.readValue(text, Filter.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            setValue(filter);
        }
    }

    public static void getFilterTable(List<Car> filterCars, Filter filter){
        int rows = filterCars.size() + 2;
        String absatz = "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        String[][] table = new String[rows][];
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        System.out.println("----------------------------------------------");
        System.out.println(now + "  Database @ with cars :" + filterCars.size() + "|");

        // Filter in table
        table[0] = new String[] {
                "ID      |",
                "Name / Query ",
                "|  type",
                "|  transmission",
                "|  PricePerDay min",
                "|  PricePerDay max",
                "|  seats",
                "|  AirCondition",
                "|  Fuel"
        };
        table[1] = new String[] {
                "Filter: |",
                filter.getQuery(),
                "|  "+String.valueOf(filter.getTypes()),
                "|  "+String.valueOf(filter.getTransmission()),
                "|  "+String.valueOf(filter.getPricePerDay().min),
                "|  "+String.valueOf(filter.getPricePerDay().max),
                "|  "+String.valueOf(filter.getSeats()),
                "|  "+String.valueOf(filter.isAirCondition()),
                "|  "+filter.getFuel()
        };
        int i = 2;
        //Cars in table
        for (Car car : filterCars) {
            table[i] = new String[] {
                    String.valueOf(car.getId())+"       |",
                    car.getName(),
                    "|  "+String.valueOf(car.getType()),
                    "|  "+String.valueOf(car.getTransmission()),
                    "|  "+String.valueOf(car.getPricePerDay()),
                    "|  "+String.valueOf(car.getPricePerDay()),
                    "|  "+String.valueOf(car.getSeats()),
                    "|  "+String.valueOf(car.isAirCondition()),
                    "|  "+"null"
            };
            i++;
        }
        int a = 1;
        for(String[] row : table){
            if(a == 1){
                System.out.println(absatz);
            }
            System.out.format("%-10s %-25s %-35s %-20s %-20s %-20s %-20s %-20s %-20s  %n", row);
            if(a == 1){
                System.out.println(absatz);
            }if(a == 2){
                System.out.println(absatz);
            }
            if(a == rows){
                System.out.println(absatz);
            }
            a++;
        }
    }


}