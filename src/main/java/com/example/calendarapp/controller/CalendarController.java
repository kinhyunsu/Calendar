package com.example.calendarapp.controller;


import java.util.*;

import com.example.calendarapp.dto.CalendarResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final Map<String, Calendar> calendars = new HashMap<>();

    public ResponseEntity<CalendarResponseDto> createCalendar(@RequestBody CalendarResponseDto calendarResponseDto) {

        Calendar calendar = new Calendar(calendarResponseDto.get)
    }






}
