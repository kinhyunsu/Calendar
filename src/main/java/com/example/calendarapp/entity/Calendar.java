package com.example.calendarapp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Calendar {

    private Long id;
    private String title;
    private String content;
    private String password;
    private String user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Calendar(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
