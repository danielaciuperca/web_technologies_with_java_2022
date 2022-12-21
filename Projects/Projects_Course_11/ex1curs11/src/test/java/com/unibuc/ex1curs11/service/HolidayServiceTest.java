package com.unibuc.ex1curs11.service;

import com.unibuc.ex1curs11.exception.DestinationNotFoundException;
import com.unibuc.ex1curs11.model.Destination;
import com.unibuc.ex1curs11.model.Holiday;
import com.unibuc.ex1curs11.repository.HolidayRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HolidayServiceTest {

    @InjectMocks
    private HolidayService holidayService;

    @Mock
    private HolidayRepository holidayRepository;

    @Mock
    private NotificationService notificationService;

    @Mock
    private DestinationService destinationService;

    @Test
    void whenDestinationExists_create_savesHoliday() {
        // Arrange
        Holiday holiday = new Holiday();
        Destination destination = new Destination();
        holiday.setDestination(destination);

        Optional<Destination> destinationOptional = Optional.of(destination);
        when(destinationService.findById(destination.getId()))
                .thenReturn(destinationOptional);
        Holiday savedHoliday = new Holiday();
        savedHoliday.setId(1);
        when(holidayRepository.save(holiday)).thenReturn(savedHoliday);

        when(notificationService.sendNotificationForVisa(destinationOptional.get()))
                .thenReturn(true);

        // Act
        Holiday result = holidayService.create(holiday);

        // Assert
        assertNotNull(result); // to make sure we have a not null result
        assertEquals(savedHoliday.getId(), result.getId());

        // we want to check that indeed the method findById() was called,
        // and that it was called with the id of the destination
        verify(destinationService).findById(destination.getId());

        // we want to check that indeed the method save() was called,
        // and that it was called with the holiday
        verify(holidayRepository).save(holiday);

        // we want to check that indeed the method sendNotificationForVisa() was called,
        // and that it was called with the destination from the Optional object
        verify(notificationService).sendNotificationForVisa(destinationOptional.get());
    }

    @Test
    void whenDestinationDoesntExist_create_throwsDestinationNotFoundException() {
        // Arrange
        Holiday holiday = new Holiday();
        Destination destination = new Destination();
        destination.setId(1);
        holiday.setDestination(destination);

        when(destinationService.findById(destination.getId()))
                .thenReturn(Optional.empty());

        // Act
        DestinationNotFoundException exception = assertThrows(DestinationNotFoundException.class,
                () -> holidayService.create(holiday));

        // Assert
        assertEquals("The destination with id " + destination.getId() + " doesn't exist.",
                exception.getMessage());
        // we want to check that indeed the method findById() was called,
        // and that it was called with the id of the destination
        verify(destinationService).findById(destination.getId());

        // we want to check that the method save() was never called
        verify(holidayRepository, times(0)).save(holiday);
        // or, more generic, we can check no method from holidayRepository was called
        verifyNoInteractions(holidayRepository);

        // we want to check that the method sendNotificationForVisa() was never called
        verifyNoInteractions(notificationService);
    }
}