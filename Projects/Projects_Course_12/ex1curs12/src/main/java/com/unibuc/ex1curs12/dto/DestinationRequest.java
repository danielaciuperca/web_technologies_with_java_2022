package com.unibuc.ex1curs12.dto;

import com.unibuc.ex1curs12.model.*;

import javax.validation.constraints.*;

public class DestinationRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String country;
    @NotNull
    private DestinationType destinationType;

    public DestinationRequest() {
    }

    public DestinationRequest(String name, String country, DestinationType destinationType) {
        this.name = name;
        this.country = country;
        this.destinationType = destinationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public DestinationType getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(DestinationType destinationType) {
        this.destinationType = destinationType;
    }
}
