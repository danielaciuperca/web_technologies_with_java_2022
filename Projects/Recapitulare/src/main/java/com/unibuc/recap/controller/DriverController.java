package com.unibuc.recap.controller;

import com.unibuc.recap.dto.CreateDriverDto;
import com.unibuc.recap.dto.UpdateDriverDto;
import com.unibuc.recap.mapper.DriverMapper;
import com.unibuc.recap.model.Driver;
import com.unibuc.recap.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;
    private final DriverMapper driverMapper;

    public DriverController(DriverService driverService, DriverMapper driverMapper) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
    }

    @PostMapping
    public ResponseEntity<Driver> create(
            @RequestBody
            @Valid
            CreateDriverDto createDriverDto
    ) {
        Driver driver = driverMapper.createDriverDtoToDriver(createDriverDto);
        Driver createdDriver = driverService.create(driver);
        return ResponseEntity.created(URI.create("/drivers/" + createdDriver.getId()))
                .body(createdDriver);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> update(
        @PathVariable
        long id,
        @RequestBody
                @Valid
        UpdateDriverDto updateDriverDto) {

        if(id != updateDriverDto.getId()) {
            throw new RuntimeException("IDs must match");
        }

        Driver driver = driverMapper.updatedriverdtotoDriver(updateDriverDto);
        Driver updatedDriver = driverService.update(driver);
        return ResponseEntity.ok()
                .body(updatedDriver);
    }

    @GetMapping
    public List<Driver> get(
            @RequestParam(required = false)
            String name,
            @RequestParam(required = false)
            String city) {

        return driverService.get(name, city);
    }





}
