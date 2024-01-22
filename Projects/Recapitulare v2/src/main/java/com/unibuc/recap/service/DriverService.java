package com.unibuc.recap.service;

import com.unibuc.recap.exception.DriverNotFoundException;
import com.unibuc.recap.exception.DriverWithSameEmailExists;
import com.unibuc.recap.model.Driver;
import com.unibuc.recap.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    
    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    public Driver create(Driver driver) {
        boolean driverWithSameEmailExists = driverRepository.existsByEmail(driver.getEmail());
        if(driverWithSameEmailExists) {
            throw new DriverWithSameEmailExists();
        }
        
        return driverRepository.save(driver);
    }

    public Driver update(Driver driver) {
        if(!driverRepository.findById(driver.getId()).isPresent()) {
            throw new DriverNotFoundException();
        }
        if(driverRepository.existsByEmailAndIdNot(driver.getEmail(), driver.getId())) {
            throw new DriverWithSameEmailExists();
        }
        return driverRepository.save(driver);
    }

    public List<Driver> get(String name, String city) {
        
        if(name == null && city == null) {
            return driverRepository.findAll();
        }
        if(name != null && city == null) {
            return driverRepository.findByName(name);
        }
        if(name == null && city != null) {
            return driverRepository.findByCity(city);
        }
        return driverRepository.findByNameAndCity(name, city);
    }
}
