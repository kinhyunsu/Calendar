package com.example.calendarapp.service;


import com.example.calendarapp.dto.CalendarRequestDto;
import com.example.calendarapp.dto.CalendarResponseDto;
import com.example.calendarapp.repository.CalendarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.calendarapp.entity.Calendar;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public CalendarResponseDto saveCalendar(CalendarRequestDto dto) {

        Calendar calendar = new Calendar(dto.getTitle(), dto.getContent());

        return calendarRepository.saveCalendar(calendar);
    }


    @Override
    public List<CalendarResponseDto> findAllCalendar() {

        return calendarRepository.findAllCalnedar();
    }

    @Override
    public CalendarResponseDto findCalendarById(Long id) {

        Calendar calendar = calendarRepository.findCalendarById(id);

        return new CalendarResponseDto(calendar);
    }

    @Override
    public CalendarResponseDto updateCalendar(Long id, String title, String content) {

        Calendar calendar = calendarRepository.findCalendarById(id);

        if (calendar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        if (title == null || contents != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title or contents is null");
        }

        calendar.updateCalendar(calendar);
        return new CalendarResponseDto(calendar);
    }

    @Override
    public CalendarResponseDto updateTitle(Long id, String title, String content) {

        Calendar calendar = calendarRepository.findCalendarById(id);
        if (calendar == null || content != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title or contents is null");
        }

        calendar.updateTitle(calendar);

        return new CalendarResponseDto(calendar);
    }

    @Override
    public void deleteCalendar(Long id) {
        if (calendar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        calendarRepository.deletCalendar(id);

    }
}
