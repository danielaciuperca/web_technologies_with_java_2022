package com.unibuc.recap.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateDriverDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String city;

    public CreateDriverDto() {
    }

    public CreateDriverDto(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
