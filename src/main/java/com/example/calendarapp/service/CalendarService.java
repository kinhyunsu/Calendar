package com.example.calendarapp.service;

import com.example.calendarapp.dto.CalendarRequestDto;
import com.example.calendarapp.dto.CalendarResponseDto;

import java.util.List;

public interface CalendarService {

    CalendarResponseDto saveCalendar(CalendarRequestDto dto);
    List<CalendarResponseDto> findAllCalendar();

    CalendarResponseDto findCalendarById(Long id);

    CalendarResponseDto updateCalendar(Long id, String title, String content);

    CalendarResponseDto updateTitle(Long id, String title, String content);

    void deleteCalendar(Long id);
}
