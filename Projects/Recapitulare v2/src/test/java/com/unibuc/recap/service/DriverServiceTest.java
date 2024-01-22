package com.unibuc.recap.service;

import com.unibuc.recap.exception.DriverNotFoundException;
import com.unibuc.recap.exception.DriverWithSameEmailExists;
import com.unibuc.recap.model.Driver;
import com.unibuc.recap.repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {
    
    @InjectMocks
    private DriverService driverService;
    
    @Mock
    private DriverRepository driverRepository;
    
    @Test
    public void whenDriverWithSameEmailExists_create_throwsException() {
        // arrange
        Driver driver = new Driver("John", "john@gmail.com", "Bucuresti");
        when(driverRepository.existsByEmail(driver.getEmail()))
                .thenReturn(true);
        
        // act
        DriverWithSameEmailExists exception = assertThrows(DriverWithSameEmailExists.class,
                () -> driverService.create(driver));
        
        // assert
        assertEquals("A driver with the same email already exists",
                exception.getMessage());
    }

    @Test
    public void whenDriverWithSameEmailDoesntExist_create_returnCreatedDriver() {
        // arrange
        Driver driver = new Driver("John", "john@gmail.com", "Bucuresti");
        when(driverRepository.existsByEmail(driver.getEmail()))
                .thenReturn(false);
        Driver createdDriver = new Driver(1,"John", "john@gmail.com", "Bucuresti");
        when(driverRepository.save(driver))
                .thenReturn(createdDriver);

        // act
        Driver driverResult = driverService.create(driver);

        // assert
        assertDriverFields(createdDriver, driverResult);
    }

    private static void assertDriverFields(Driver expectedDriver, Driver actualDriver) {
        assertNotNull(actualDriver);
        assertEquals(expectedDriver.getId(), actualDriver.getId());
        assertEquals(expectedDriver.getName(), actualDriver.getName());
        assertEquals(expectedDriver.getEmail(), actualDriver.getEmail());
        assertEquals(expectedDriver.getCity(), actualDriver.getCity());
    }

    @Test
    public void whenDriverDoesntExist_update_throwsException() {
        // arrange
        Driver driver = new Driver("John", "john@gmail.com", "Bucuresti");
        when(driverRepository.findById(driver.getId()))
                .thenReturn(Optional.empty());

        // act
        DriverNotFoundException exception = assertThrows(DriverNotFoundException.class,
                () -> driverService.update(driver));

        // assert
        assertEquals("The driver does not exist.",
                exception.getMessage());
    }

    @Test
    public void whenAnotherDriverWithSameEmailExists_update_throwsException() {
        // arrange
        Driver driver = new Driver("John", "john@gmail.com", "Bucuresti");
        when(driverRepository.findById(driver.getId()))
                .thenReturn(Optional.of(driver));
        when(driverRepository.existsByEmailAndIdNot(driver.getEmail(), driver.getId()))
                .thenReturn(true);

        // act
        DriverWithSameEmailExists exception = assertThrows(DriverWithSameEmailExists.class,
                () -> driverService.update(driver));

        // assert
        assertEquals("A driver with the same email already exists",
                exception.getMessage());
    }

    @Test
    public void update_returnUpdatedDriver() {
        // arrange
        Driver driver = new Driver("John", "john@gmail.com", "Bucuresti");
        when(driverRepository.findById(driver.getId()))
                .thenReturn(Optional.of(driver));
        when(driverRepository.existsByEmailAndIdNot(driver.getEmail(), driver.getId()))
                .thenReturn(false);
        Driver updatedDriver = new Driver(1,"John", "john@gmail.com", "Bucuresti");
        when(driverRepository.save(driver))
                .thenReturn(updatedDriver);
        // act
        Driver driverResult = driverService.update(driver);

        // assert
        assertDriverFields(updatedDriver, driverResult);
    }

    @Test
    public void whenNameAndCityNotProvided_get_returnsDrivers() {
        // arrange
        List<Driver> drivers = getDrivers();
        when(driverRepository.findAll())
                .thenReturn(drivers);
        
        // act
        List<Driver> result = driverService.get(null, null);

        // assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertDriverFields(drivers.get(0), result.get(0));
        assertDriverFields(drivers.get(1), result.get(1));
        verifyNoMoreInteractions(driverRepository);
    }

    @Test
    public void whenName_get_returnsDrivers() {
        // arrange
        Driver driver = getDrivers().get(0);
        when(driverRepository.findByName(driver.getName()))
                .thenReturn(List.of(driver));

        // act
        List<Driver> result = driverService.get("John", null);

        // assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertDriverFields(driver, result.get(0));
        verifyNoMoreInteractions(driverRepository);
    }

    @Test
    public void whenCity_get_returnsDrivers() {
        // arrange
        Driver driver = getDrivers().get(0);
        when(driverRepository.findByCity(driver.getCity()))
                .thenReturn(List.of(driver));

        // act
        List<Driver> result = driverService.get(null, "Bucuresti");

        // assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertDriverFields(driver, result.get(0));
        verifyNoMoreInteractions(driverRepository);
    }

    @Test
    public void whenNameAndCity_get_returnsDrivers() {
        // arrange
        Driver driver = getDrivers().get(0);
        when(driverRepository.findByNameAndCity(driver.getName(), driver.getCity()))
                .thenReturn(List.of(driver));

        // act
        List<Driver> result = driverService.get("John", "Bucuresti");

        // assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertDriverFields(driver, result.get(0));
        verify(driverRepository, times(0)).findAll();
    }

    private static List<Driver> getDrivers() {
        Driver driver1 = new Driver(1, "John", "john@gmail.com", "Bucuresti");
        Driver driver2 = new Driver(2, "Mary", "mary@gmail.com", "Bucuresti");
        return List.of(driver1, driver2);
    }

}