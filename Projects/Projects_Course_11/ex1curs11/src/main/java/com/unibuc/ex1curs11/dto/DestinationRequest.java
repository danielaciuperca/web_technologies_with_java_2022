package com.unibuc.ex1curs11.dto;

import com.unibuc.ex1curs11.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

@ApiModel(value = "Destination request", description = "Required details needed to create a new Destination")
public class DestinationRequest {
    @NotBlank
    @ApiModelProperty(value = "name", required = true, notes = "The name of the Destination", example = "New York", position = 1)
    private String name;
    @NotBlank
    @ApiModelProperty(value = "country", required = true, notes = "The country of the Destination", example = "USA", position = 2)
    private String country;
    @NotNull
    @ApiModelProperty(value = "destinationType", required = true, notes = "The type of the Destination", example = "CITY", position = 3)
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
