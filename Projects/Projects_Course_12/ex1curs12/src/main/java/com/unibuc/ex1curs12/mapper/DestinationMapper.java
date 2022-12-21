package com.unibuc.ex1curs12.mapper;

import com.unibuc.ex1curs12.dto.*;
import com.unibuc.ex1curs12.model.*;
import org.springframework.stereotype.*;

@Component
public class DestinationMapper {
    public Destination destinationRequestToDestination(DestinationRequest destinationRequest) {
        return new Destination(destinationRequest.getName(), destinationRequest.getCountry(), destinationRequest.getDestinationType());
    }
}
