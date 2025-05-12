package com.example.calendarapp.dto;

import lombok.Getter;

@Getter
public class CalendarResponseDto {

    private String name;

    private String schedule;

    private String password;

    private String date;

    public CalendarResponseDto(Calendar calendar) {
        this.name = calendar.getName();
        this.schedule = calendar.getSchedule();
        this.password = calendar.getPassword();
        this.date = calendar.getDate();

    }


}
