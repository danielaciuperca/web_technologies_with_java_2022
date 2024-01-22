package com.unibuc.recap.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CreateDriverRequestDto {
    
    @NotBlank
    @Length(max = 100)
    private String name;
    @NotBlank
    @Length(max = 100)
    private String email;
    @NotBlank
    @Length(max = 100)
    private String city;

    public CreateDriverRequestDto() {
    }

    public CreateDriverRequestDto(String name, String email, String city) {
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
