package com.example.calendarapp.repository;

import com.example.calendarapp.dto.CalendarResponseDto;
import com.example.calendarapp.entity.Calendar;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository {

    CalendarResponseDto saveCalendar(Calendar calendar);
    List<CalendarResponseDto> findAllCalendar();

    Calendar findCalendarByIdOrThrow(Long id);

    int updateCalendar(Long id, String title, String content);
    int updateTitle(Long id, String title, String content);
    int deleteCalendar(Long id);

}
