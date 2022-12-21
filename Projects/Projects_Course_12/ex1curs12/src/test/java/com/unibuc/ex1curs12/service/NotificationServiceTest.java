package com.unibuc.ex1curs12.service;

import com.unibuc.ex1curs12.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationServiceTest {

    private NotificationService notificationService = new NotificationService();

    @BeforeEach //this method is invoked before the execution of every test
    void setUp() {
        //common code, useful for every test in this class
    }

    @BeforeAll //this method executes only once, before all tests (when the test class is prepared to be run)
    static void beforeAll() {
        //connection to a db for an integration test
        //preparing a file
        //setting of application properties
    }

    @AfterEach //this method is invoked after the execution of every test
    void tearDown() {

    }

    @AfterAll //this method executes only once, after all tests
    static void afterAll() {
        //delete the file
    }

    @Test
    @DisplayName("Send notification for visa when destination is null")
    void sendNotificationForVisaDestinationNull() {
        //arrange (given)
        Destination destination = null;

        //act (when)
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> notificationService.sendNotificationForVisa(destination));

        //assert (then)
        assertEquals("Cannot send notification. Destination is null.",
                exception.getMessage());
    }

    @Test
    @DisplayName("Send notification for visa when visa is required for the destination's country")
    void sendNotificationForVisaVisaRequired() {
        //arrange
        Destination destination = new Destination();
        destination.setCountry("USA");

        //act
        boolean result = notificationService.sendNotificationForVisa(destination);

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Send notification for visa when visa is not required for the destination's country")
    void sendNotificationForVisaVisaNotRequired() {
        //arrange
        Destination destination = new Destination();
        destination.setCountry("Romania");

        //act
        boolean result = notificationService.sendNotificationForVisa(destination);

        //assert
        assertFalse(result);
    }
}