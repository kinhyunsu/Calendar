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


    public Calendar(String title, String content, String user, String password) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title) {this.title = title;}
}
