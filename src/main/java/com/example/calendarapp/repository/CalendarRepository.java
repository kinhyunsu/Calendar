package com.example.calendarapp.repository;

import com.example.calendarapp.dto.CalendarResponseDto;
import com.example.calendarapp.entity.Calendar;

import java.util.List;

public interface CalendarRepository {

    CalendarResponseDto saveCalendar(Calendar calendar);

    List<CalendarResponseDto> findAllCalendar();

    Calendar findCalendarById(Long id);

    void deletCalendar(Long id);
}
