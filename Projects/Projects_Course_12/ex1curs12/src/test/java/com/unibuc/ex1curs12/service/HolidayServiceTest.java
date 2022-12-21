package com.unibuc.ex1curs12.service;

import com.unibuc.ex1curs12.model.*;
import com.unibuc.ex1curs12.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HolidayServiceTest {
    @Mock
    private HolidayRepository holidayRepository;
    @Mock
    private NotificationService notificationService;
    @Mock
    private DestinationService destinationService;
    @InjectMocks
    private HolidayService holidayService;

    @Test
    @DisplayName("Create holiday - happy path")
    void create() {
        //arrange
        Destination destination = new Destination();
        destination.setName("Paris");
        Holiday holiday = new Holiday();
        holiday.setDestination(destination);

        when(destinationService.findById(anyLong()))
                .thenReturn(Optional.of(destination));
        Holiday savedHoliday = new Holiday();
        savedHoliday.setId(1000);
        when(holidayRepository.save(any()))
                .thenReturn(savedHoliday);
        when(notificationService.sendNotificationForVisa(any()))
                .thenReturn(false);

        //act
        Holiday result = holidayService.create(holiday);

        //assert
        assertNotNull(result);
        assertEquals(savedHoliday.getId(), result.getId());
    }
}