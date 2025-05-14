package com.example.calendarapp.service;


import com.example.calendarapp.dto.CalendarRequestDto;
import com.example.calendarapp.dto.CalendarResponseDto;
import com.example.calendarapp.repository.CalendarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        Calendar calendar = new Calendar(dto.getTitle(), dto.getContent(), dto.getUser(), dto.getPassword());

        return calendarRepository.saveCalendar(calendar);
    }


    @Override
    public List<CalendarResponseDto> findAllCalendar() {


        return calendarRepository.findAllCalendar();
    }







    @Override
    public CalendarResponseDto findCalendarById(Long id) {

        Calendar calendar = calendarRepository.findCalendarByIdOrThrow(id);

        return new CalendarResponseDto(calendar);
    }



    @Transactional
    @Override
    public CalendarResponseDto updateCalendar(Long id, String title, String content) {

        if (title == null || content == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title or contents is null");
        }

        int updatedRow = calendarRepository.updateCalendar(id, title, content);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Calendar calendar = calendarRepository.findCalendarByIdOrThrow(id);

        return new CalendarResponseDto(calendar);
    }




    @Transactional
    @Override
    public CalendarResponseDto updateTitle(Long id, String title, String content) {


        if (title == null || content != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title or contents is null");
        }

        int updatedRow = calendarRepository.updateTitle(id, title, content);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Calendar calendar = calendarRepository.findCalendarByIdOrThrow(id);

        return new CalendarResponseDto(calendar);
    }




    @Override
    public void deleteCalendar(Long id) {

        int deletedRow = calendarRepository.deleteCalendar(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }




    }
}
