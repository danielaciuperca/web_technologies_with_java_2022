package com.unibuc.ex1curs11.controller;

import com.unibuc.ex1curs11.dto.*;
import com.unibuc.ex1curs11.mapper.*;
import com.unibuc.ex1curs11.model.*;
import com.unibuc.ex1curs11.service.*;
import io.swagger.annotations.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;

@RestController
@RequestMapping("/destinations")
@Api(value = "/destinations",
        tags = "Destinations")
public class DestinationController {
    private DestinationService destinationService;
    private DestinationMapper destinationMapper;

    public DestinationController(DestinationService destinationService, DestinationMapper destinationMapper) {
        this.destinationService = destinationService;
        this.destinationMapper = destinationMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create a Destination",
            notes = "Creates a new Destination based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Destination was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Destination> create(
            @RequestBody
            @ApiParam(name = "destination", value = "Destination details", required = true)
                    DestinationRequest destinationRequest) {
        Destination savedDestination = destinationService.create(
                destinationMapper.destinationRequestToDestination(destinationRequest));
        return ResponseEntity
                .created(URI.create("/destinations/" + savedDestination.getId()))
                .body(savedDestination);
    }
}
