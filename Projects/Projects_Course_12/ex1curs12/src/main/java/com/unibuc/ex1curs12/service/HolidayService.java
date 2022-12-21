package com.unibuc.ex1curs12.service;

import com.unibuc.ex1curs12.exception.*;
import com.unibuc.ex1curs12.model.*;
import com.unibuc.ex1curs12.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class HolidayService {

    private HolidayRepository holidayRepository;
    private NotificationService notificationService;
    private DestinationService destinationService;

    public HolidayService(HolidayRepository holidayRepository, NotificationService notificationService, DestinationService destinationService) {
        this.holidayRepository = holidayRepository;
        this.notificationService = notificationService;
        this.destinationService = destinationService;
    }

    public Holiday create(Holiday holiday) {
        Optional<Destination> destination = destinationService.findById(holiday.getDestination().getId());
        if(destination.isEmpty()) {
            throw new DestinationNotFoundException(holiday.getDestination().getId());
        }
        holiday.setDestination(destination.get());
        Holiday savedHoliday = holidayRepository.save(holiday);
        notificationService.sendNotificationForVisa(destination.get());
        return savedHoliday;
    }
}
