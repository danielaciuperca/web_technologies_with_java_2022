package com.unibuc.ex1curs11.service;

import com.unibuc.ex1curs11.model.Destination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private NotificationService notificationService = new NotificationService();

    @Test
    @DisplayName("When destination parameter is null, sendNotificationForVisa throws RuntimeException")
    void whenDestinationIsNull_sendNotificationForVisa_throwsRuntimeException() {
        // Arrange
        Destination destination = null;

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> notificationService.sendNotificationForVisa(destination));

        assertEquals("Cannot send notification. " +
                "Destination is null.", exception.getMessage());

    }

    @Test
    void whenCountryNeedsVisa_sendNotificationForVisa_returnsTrue() {
        // Arrange
        Destination destination = new Destination();
        destination.setCountry("CUBA");

        // Act
        boolean result = notificationService.sendNotificationForVisa(destination);

        // Assert
        assertTrue(result);
    }

    @Test
    void whenCountryDoesntNeedVisa_sendNotificationForVisa_returnsFalse() {
        // Arrange
        Destination destination = new Destination();
        destination.setCountry("ROMANIA");

        // Act
        boolean result = notificationService.sendNotificationForVisa(destination);

        // Assert
        assertFalse(result);
    }

















}