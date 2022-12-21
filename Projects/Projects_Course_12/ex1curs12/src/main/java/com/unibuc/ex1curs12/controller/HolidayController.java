package com.unibuc.ex1curs12.controller;

import com.unibuc.ex1curs12.dto.*;
import com.unibuc.ex1curs12.mapper.*;
import com.unibuc.ex1curs12.model.*;
import com.unibuc.ex1curs12.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;

@RestController
@RequestMapping("/holidays")
public class HolidayController {
    private HolidayService holidayService;
    private HolidayMapper holidayMapper;

    public HolidayController(HolidayService holidayService, HolidayMapper holidayMapper) {
        this.holidayService = holidayService;
        this.holidayMapper = holidayMapper;
    }

    @PostMapping
    public ResponseEntity<Holiday> create(
            @RequestBody
                    HolidayRequest holidayRequest) {
        Holiday savedHoliday = holidayService.create(holidayMapper.holidayRequestToHoliday(holidayRequest));
        return ResponseEntity.created(URI.create("/holidays/" + savedHoliday.getId()))
                .body(savedHoliday);
    }
}
