package com.unibuc.ex1curs11.service;

import com.unibuc.ex1curs11.exception.DuplicateDestinationException;
import com.unibuc.ex1curs11.model.Destination;
import com.unibuc.ex1curs11.repository.DestinationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DestinationServiceTest {

    @InjectMocks
    private DestinationService destinationService;

    @Mock
    private DestinationRepository destinationRepository;

    @Test
    void whenDestinationAlreadyExists_create_throwsDuplicateDestinationException() {
        // Arrange
        Destination destination = new Destination();
        destination.setName("Bali");
        when(destinationRepository.findByName(destination.getName()))
                .thenReturn(Optional.of(destination));

        // Act
        DuplicateDestinationException exception = assertThrows(DuplicateDestinationException.class,
                () -> destinationService.create(destination));

        // Assert
        assertEquals("A destination with the same name already exists.", exception.getMessage());
        verify(destinationRepository, times(0)).save(destination);

//        !!! here I cannot check with verifyNoInteractions(), because the execution has one interaction
//        with destinationRepository, when calling the findByName() method. so I have to be specific,
//        and check only that the execution didn't call the save() method
    }

    @Test
    void whenDestinationDoesntExist_create_savesTheDestination() {
        // Arrange
        Destination destination = new Destination();
        destination.setName("Bali");
        when(destinationRepository.findByName(destination.getName()))
                .thenReturn(Optional.empty());
        Destination savedDestination = new Destination();
        savedDestination.setName("Bali");
        savedDestination.setId(1);
        when(destinationRepository.save(destination)).thenReturn(savedDestination);

        // Act
        Destination result = destinationService.create(destination);

        // Assert
        assertNotNull(result);
        assertEquals(savedDestination.getId(), result.getId());
        assertEquals(savedDestination.getName(), result.getName());
        assertEquals(destination.getName(), result.getName());
        verify(destinationRepository).findByName(destination.getName());
        verify(destinationRepository).save(destination);
    }

    @Test
    void whenDestinationExists_findById_returnsTheDestination() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1);
        when(destinationRepository.findById(1L)).thenReturn(Optional.of(destination));

        // Act
        Optional<Destination> result = destinationService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(destination.getId(), result.get().getId());
    }

    @Test
    void whenDestinationDoesntExists_findById_returnsEmptyOptional() {
        // Arrange
        when(destinationRepository.findById(1L)).thenReturn(Optional.empty());
        // Act
        Optional<Destination> result = destinationService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}