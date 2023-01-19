package com.unibuc.recap.service;

import com.unibuc.recap.exception.DriverAlreadyExistsException;
import com.unibuc.recap.exception.DriverNotFoundException;
import com.unibuc.recap.model.Driver;
import com.unibuc.recap.repository.DriverRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Test
    void create() {
        //arrange
        Driver driver = new Driver("John", "john@gmail.com", "Bucharest");
        when(driverRepository.findByEmail(driver.getEmail()))
                .thenReturn(Optional.empty());
        Driver savedDriver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        when(driverRepository.save(driver)).thenReturn(savedDriver);

        //act
        Driver result = driverService.create(driver);

        //assert
        assertNotNull(result);
        assertEquals(savedDriver.getId(), result.getId());
        assertEquals(savedDriver.getName(), result.getName());
        assertEquals(savedDriver.getEmail(), result.getEmail());
        assertEquals(savedDriver.getCity(), result.getCity());

        verify(driverRepository).findByEmail(driver.getEmail());
        verify(driverRepository).save(driver);
    }

    @Test
    void whenEmail_alreadyExists_create_throwsException() {
        //arrange
        Driver driver = new Driver("John", "john@gmail.com", "Bucharest");
        when(driverRepository.findByEmail(driver.getEmail()))
                .thenReturn(Optional.of(driver));

        //act
        DriverAlreadyExistsException exception = assertThrows(DriverAlreadyExistsException.class,
                () -> driverService.create(driver));

        //assert
        assertNotNull(exception);
        assertEquals("A driver with the same email already exists.",
                exception.getMessage());

        verify(driverRepository).findByEmail(driver.getEmail());
        verify(driverRepository, times(0)).save(driver);
    }

    @Test
    void whenNameAndCity_areChanged_update_isSuccessful() {
        //arrange
        Driver oldDriver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        Driver newDriver = new Driver(1, "Jonathan", "john@gmail.com", "Brasov");
        when(driverRepository.findById(newDriver.getId()))
                .thenReturn(Optional.of(oldDriver));
        when(driverRepository.save(newDriver)).thenReturn(newDriver);

        //act
        Driver result = driverService.update(newDriver);

        //assert
        assertNotNull(result);
        assertEquals(newDriver.getId(), result.getId());
        assertEquals(newDriver.getName(), result.getName());
        assertEquals(newDriver.getEmail(), result.getEmail());
        assertEquals(newDriver.getCity(), result.getCity());

        verify(driverRepository).findById(newDriver.getId());
        verify(driverRepository).save(newDriver);
        verify(driverRepository, never()).findByEmail(newDriver.getEmail());
    }

    @Test
    void whenEmail_isChanged_andItDoesNotAlreadyExist_update_isSuccessful() {
        //arrange
        Driver oldDriver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        Driver newDriver = new Driver(1, "John", "johnny@gmail.com", "Bucharest");
        when(driverRepository.findById(newDriver.getId()))
                .thenReturn(Optional.of(oldDriver));
        when(driverRepository.findByEmailAndIdNot(newDriver.getEmail(), newDriver.getId()))
                .thenReturn(Optional.empty());
        when(driverRepository.save(newDriver))
                .thenReturn(newDriver);

        //act
        Driver result = driverService.update(newDriver);

        //assert
        assertNotNull(result);
        assertEquals(newDriver.getId(), result.getId());
        assertEquals(newDriver.getName(), result.getName());
        assertEquals(newDriver.getEmail(), result.getEmail());
        assertEquals(newDriver.getCity(), result.getCity());

        verify(driverRepository).findById(newDriver.getId());
        verify(driverRepository).findByEmailAndIdNot(newDriver.getEmail(), newDriver.getId());
        verify(driverRepository).save(newDriver);
    }

    @Test
    void whenEmail_isChanged_andItAlreadyExists_update_throwsException() {
        //arrange
        Driver oldDriver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        Driver newDriver = new Driver(1, "John", "johnny@gmail.com", "Bucharest");
        Driver anotherDriver = new Driver(2, "Billy John", "johnny@gmail.com", "Iasi");
        when(driverRepository.findById(newDriver.getId()))
                .thenReturn(Optional.of(oldDriver));
        when(driverRepository.findByEmailAndIdNot(newDriver.getEmail(), newDriver.getId()))
                .thenReturn(Optional.of(anotherDriver));

        //act
        DriverAlreadyExistsException exception = assertThrows(DriverAlreadyExistsException.class,
                () -> driverService.update(newDriver));

        //assert
        assertNotNull(exception);
        assertEquals("A driver with the same email already exists.",
                exception.getMessage());

        verify(driverRepository).findById(newDriver.getId());
        verify(driverRepository).findByEmailAndIdNot(newDriver.getEmail(), newDriver.getId());
        verify(driverRepository, times(0)).save(newDriver);
    }

    @Test
    void whenDriver_doesNotExist_update_throwsException() {
        //arrange
        Driver newDriver = new Driver(1, "John", "johnny@gmail.com", "Bucharest");
        when(driverRepository.findById(newDriver.getId()))
                .thenReturn(Optional.empty());

        //act
        DriverNotFoundException exception = assertThrows(DriverNotFoundException.class,
                () -> driverService.update(newDriver));

        //assert
        assertNotNull(exception);
        assertEquals("The driver doesn't exist.",
                exception.getMessage());

        verify(driverRepository).findById(newDriver.getId());
        verify(driverRepository, times(0)).findByEmailAndIdNot(newDriver.getEmail(), newDriver.getId());
        verify(driverRepository, times(0)).save(newDriver);
    }

    @Test
    @DisplayName("Get drivers by name")
    void getByName() {
        //arrange
        String name = "John";
        Driver driver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        when(driverRepository.findByName(name))
                .thenReturn(List.of(driver));

        //act
        List<Driver> result = driverService.get(name, null);

        //assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driver, result.get(0));

        verify(driverRepository).findByName(name);
        verify(driverRepository, never()).findByNameAndCity(any(), any());
        verify(driverRepository, never()).findByCity(any());
        verify(driverRepository, never()).findAll();
    }

    @Test
    @DisplayName("Get drivers by city")
    void getByCity() {
        //arrange
        String city = "Bucharest";
        Driver driver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        when(driverRepository.findByCity(city))
                .thenReturn(List.of(driver));

        //act
        List<Driver> result = driverService.get(null, city);

        //assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driver, result.get(0));

        verify(driverRepository, never()).findByName(any());
        verify(driverRepository, never()).findByNameAndCity(any(), any());
        verify(driverRepository).findByCity(city);
        verify(driverRepository, never()).findAll();
    }

    @Test
    @DisplayName("Get drivers by name and city")
    void getByNameAndCity() {
        //arrange
        String name = "John";
        String city = "Bucharest";
        Driver driver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        when(driverRepository.findByNameAndCity(name, city))
                .thenReturn(List.of(driver));

        //act
        List<Driver> result = driverService.get(name, city);

        //assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driver, result.get(0));

        verify(driverRepository, never()).findByName(any());
        verify(driverRepository).findByNameAndCity(name, city);
        verify(driverRepository, never()).findByCity(any());
        verify(driverRepository, never()).findAll();
    }

    @Test
    @DisplayName("Get all drivers - no filters by name and city")
    void getAll() {
        //arrange
        Driver driver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        when(driverRepository.findAll()).thenReturn(List.of(driver));

        //act
        List<Driver> result = driverService.get(null, null);

        //assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driver, result.get(0));

        verify(driverRepository, never()).findByName(any());
        verify(driverRepository, never()).findByNameAndCity(any(), any());
        verify(driverRepository, never()).findByCity(any());
        verify(driverRepository).findAll();
    }
}