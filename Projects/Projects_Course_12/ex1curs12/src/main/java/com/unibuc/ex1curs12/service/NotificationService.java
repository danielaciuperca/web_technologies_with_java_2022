package com.unibuc.ex1curs12.service;

import com.unibuc.ex1curs12.model.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class NotificationService {

    private List<String> countriesVisaRequired = List.of("USA", "CUBA", "THAILAND");

    public boolean sendNotificationForVisa(Destination destination) {
        boolean visaRequired = false;
        if(destination == null) {
            throw new RuntimeException("Cannot send notification. Destination is null.");
        }
        if(countriesVisaRequired.contains(destination.getCountry())) {
            visaRequired = true;
            System.out.println("A VISA is required for destination  " + destination.getName() +
                    ", " + destination.getCountry());
        }
        return visaRequired;
    }
}
