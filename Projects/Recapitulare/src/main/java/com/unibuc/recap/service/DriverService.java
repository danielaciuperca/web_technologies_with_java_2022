package com.unibuc.recap.service;

import com.unibuc.recap.exception.DriverAlreadyExistsException;
import com.unibuc.recap.exception.DriverNotFoundException;
import com.unibuc.recap.model.Driver;
import com.unibuc.recap.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    public Driver create(Driver driver) {
        Optional<Driver> driverWithSameEmail = driverRepository.findByEmail(driver.getEmail());
        if(driverWithSameEmail.isPresent()) {
            throw new DriverAlreadyExistsException();
        }
        return driverRepository.save(driver);
    }

    public Driver update(Driver driver) {
        Optional<Driver> existingDriver = driverRepository.findById(driver.getId());
        if(existingDriver.isEmpty()) {
            throw new DriverNotFoundException();
        }
        Optional<Driver> driverWithSameEmail =
                driverRepository.findByEmailAndIdNot(driver.getEmail(), driver.getId());
        if(driverWithSameEmail.isPresent()) {
            throw new DriverAlreadyExistsException();
        }

        return driverRepository.save(driver);
    }

    public List<Driver> get(String name, String city) {

        List<Driver> drivers = new ArrayList<>();

        if(name != null && !name.isEmpty()) {
            if(city != null && !city.isEmpty()) {
                // search by name and city
                drivers = driverRepository.findByNameAndCity(name, city);
            } else {
                // search by name
                drivers = driverRepository.findByName(name);
            }
        } else {
            if(city != null && !city.isEmpty()) {
                // search by city
                drivers = driverRepository.findByCity(city);
            } else {
                // find all
                drivers = driverRepository.findAll();
            }
        }
        return drivers;
    }
}
