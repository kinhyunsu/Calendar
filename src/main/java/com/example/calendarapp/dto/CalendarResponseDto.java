package com.example.calendarapp.dto;

import com.example.calendarapp.entity.Calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CalendarResponseDto {
    private Long id;
    private String title;
    private String content;
    private String user;
    private LocalDateTime updatedAt;

    public CalendarResponseDto(Calendar calendar){
        this.id = calendar.getId();
        this.title = calendar.getTitle();
        this.content = calendar.getContent();
        this.user = calendar.getUser();
        this.updatedAt = calendar.getUpdatedAt();
    }
}