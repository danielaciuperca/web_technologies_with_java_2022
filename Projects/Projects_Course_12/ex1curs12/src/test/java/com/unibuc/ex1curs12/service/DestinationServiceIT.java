package com.unibuc.ex1curs12.service;

import com.unibuc.ex1curs12.model.*;
import com.unibuc.ex1curs12.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.*;

import java.util.*;

import static com.unibuc.ex1curs12.model.DestinationType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test") //the test will use the configuration from the application-test.properties file
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DestinationServiceIT {

    @MockBean
    private DestinationRepository destinationRepository;

    @Autowired
    private DestinationService destinationService;

    @Test
    @DisplayName("Create destination - happy flow")
    public void createDestination() {

        // Arrange
        Destination destination = new Destination("New York", "USA", CITY);
        when(destinationRepository.findByName(destination.getName())).thenReturn(Optional.empty());

        Destination savedDestination = new Destination(1, "New York", "USA", CITY);
        when(destinationRepository.save(destination)).thenReturn(savedDestination);

        // Act
        Destination result = destinationService.create(destination);


        // Assert
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(savedDestination.getId(), result.getId());
        assertEquals(savedDestination.getName(), result.getName());
        assertEquals(savedDestination.getCountry(), result.getCountry());
        assertEquals(savedDestination.getDestinationType(), result.getDestinationType());
    }

}
