package com.unibuc.recap.controller;

import com.unibuc.recap.dto.CreateDriverRequestDto;
import com.unibuc.recap.dto.DriverResponseDto;
import com.unibuc.recap.dto.UpdateDriverRequestDto;
import com.unibuc.recap.mapper.DriverMapper;
import com.unibuc.recap.model.Driver;
import com.unibuc.recap.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    
    private DriverService driverService;
    private DriverMapper driverMapper;

    public DriverController(DriverService driverService, DriverMapper driverMapper) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
    }
    
    @PostMapping
    public ResponseEntity<DriverResponseDto> create(
            @Valid
            @RequestBody
            CreateDriverRequestDto requestDto
    ) {
        Driver driver = driverMapper.createDriverRequestDtoToDriver(requestDto);
        DriverResponseDto response = driverMapper.driverToDriverResponseDto(
                driverService.create(driver));
        return ResponseEntity.created(URI.create("/drivers" + response.getId()))
                .body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DriverResponseDto> update(
            @PathVariable
            Long id,
            
            @Valid
            @RequestBody
            UpdateDriverRequestDto request            
    ) {
        if(id != request.getId()) {
            throw new RuntimeException("The id from the url must match the id from the request body");
        }
        
        Driver driver = driverMapper.updateDriverRequestDtoToDriver(request);
        Driver updatedDriver = driverService.update(driver);
        DriverResponseDto response = driverMapper.driverToDriverResponseDto(updatedDriver);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public List<DriverResponseDto> get(
            @RequestParam(required = false)
            String name,
            @RequestParam(required = false)
            String city
    ) {
        List<Driver> drivers = driverService.get(name, city);
        return drivers.stream()
                .map(d -> driverMapper.driverToDriverResponseDto(d))
                .toList();
    }
}
