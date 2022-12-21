package com.unibuc.ex1curs12.dto;

import com.unibuc.ex1curs12.model.*;

import javax.validation.constraints.*;

public class HolidayRequest {
    @NotBlank
    private String accommodation;
    @NotNull
    @Min(1)
    private int duration;
    @NotBlank
    private String transportation;
    @NotNull
    @Min(1)
    private double price;
    @NotNull
    private Destination destination;

    public HolidayRequest() {
    }

    public HolidayRequest(String accommodation, int duration, String transportation, double price, Destination destination) {
        this.accommodation = accommodation;
        this.duration = duration;
        this.transportation = transportation;
        this.price = price;
        this.destination = destination;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
