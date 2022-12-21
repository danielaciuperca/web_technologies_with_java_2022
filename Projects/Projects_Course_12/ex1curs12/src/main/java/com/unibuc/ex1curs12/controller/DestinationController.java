package com.unibuc.ex1curs12.controller;

import com.unibuc.ex1curs12.dto.*;
import com.unibuc.ex1curs12.mapper.*;
import com.unibuc.ex1curs12.model.*;
import com.unibuc.ex1curs12.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;

@RestController
@RequestMapping("/destinations")
public class DestinationController {
    private DestinationService destinationService;
    private DestinationMapper destinationMapper;

    public DestinationController(DestinationService destinationService, DestinationMapper destinationMapper) {
        this.destinationService = destinationService;
        this.destinationMapper = destinationMapper;
    }

    @PostMapping
    public ResponseEntity<Destination> create(
            @RequestBody DestinationRequest destinationRequest) {
        Destination savedDestination = destinationService.create(
                destinationMapper.destinationRequestToDestination(destinationRequest));
        return ResponseEntity
                .created(URI.create("/destinations/" + savedDestination.getId()))
                .body(savedDestination);
    }
}
