package com.unibuc.ex1curs12.controller;

import com.fasterxml.jackson.databind.*;
import com.unibuc.ex1curs12.dto.*;
import com.unibuc.ex1curs12.mapper.*;
import com.unibuc.ex1curs12.model.*;
import com.unibuc.ex1curs12.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DestinationController.class) //this tells Spring Boot to auto-configure a Spring web context
                                                // for integration tests for the DestinationController class
public class DestinationControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DestinationService destinationService;
    @MockBean
    private DestinationMapper destinationMapper;

    @Test
    public void createDestination() throws Exception {
        DestinationRequest request = new DestinationRequest("New York", "USA", DestinationType.CITY);

        when(destinationService.create(any())).thenReturn(new Destination(1, "New York", "USA", DestinationType.CITY));

        mockMvc.perform(post("/destinations")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.country").value(request.getCountry()));
    }
}
