package com.example.calendarapp.controller;


import com.example.calendarapp.dto.CalendarRequestDto;
import com.example.calendarapp.dto.CalendarResponseDto;
import com.example.calendarapp.service.CalendarService;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ReadingConverter
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping
    public ResponseEntity<CalendarResponseDto> createCalendar(@RequestBody CalendarRequestDto dto) {

        return new ResponseEntity<>(calendarService.saveCalendar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CalendarResponseDto> findAllCalendar() {

        return calendarService.findAllCalendar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> findCalendarById(@PathVariable Long id) {

        return new ResponseEntity<>(calendarService.findCalendarById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> updateCalendar(
        @PathVariable Long id,
        @RequestBody CalendarRequestDto dto
    ) {
        return new ResponseEntity<>(calendarService.updateCalendar(id, dto.getTitle(), dto.getContent()), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> updateTitle(
        @PathVariable Long id,
        @RequestBody CalendarRequestDto dto
    ) {
        return new ResponseEntity<>(calendarService.updateTitle(id, dto.getTitle(), dto.getContent()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable Long id) {
        calendarService.deleteCalendar(id);
    }





}
