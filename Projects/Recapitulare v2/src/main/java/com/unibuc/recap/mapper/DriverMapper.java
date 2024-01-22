package com.unibuc.recap.mapper;

import com.unibuc.recap.dto.CreateDriverRequestDto;
import com.unibuc.recap.dto.DriverResponseDto;
import com.unibuc.recap.dto.UpdateDriverRequestDto;
import com.unibuc.recap.model.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
    
    public Driver createDriverRequestDtoToDriver(CreateDriverRequestDto request) {
        return new Driver(request.getName(), request.getEmail(), request.getCity());
    }

    public Driver updateDriverRequestDtoToDriver(UpdateDriverRequestDto request) {
        return new Driver(request.getId(), request.getName(), request.getEmail(), request.getCity());
    }

    public DriverResponseDto driverToDriverResponseDto(Driver model) {
        return new DriverResponseDto(model.getId(), model.getName(), model.getEmail(), model.getCity());
    }
}
