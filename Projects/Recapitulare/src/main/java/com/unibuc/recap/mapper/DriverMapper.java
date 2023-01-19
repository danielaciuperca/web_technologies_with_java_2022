package com.unibuc.recap.mapper;

import com.unibuc.recap.dto.CreateDriverDto;
import com.unibuc.recap.dto.UpdateDriverDto;
import com.unibuc.recap.model.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    public Driver createDriverDtoToDriver(CreateDriverDto createDriverDto) {
        return new Driver(
                createDriverDto.getName(),
                createDriverDto.getEmail(),
                createDriverDto.getCity()
        );
    }

    public Driver updatedriverdtotoDriver(UpdateDriverDto updateDriverDto) {
        return new Driver(updateDriverDto.getId(),
                updateDriverDto.getName(),
                updateDriverDto.getEmail(),
                updateDriverDto.getCity());
    }
}
