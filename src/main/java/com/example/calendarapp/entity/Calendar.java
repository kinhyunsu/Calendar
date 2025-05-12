package com.example.calendarapp.entity;


import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity

public class Calendar {

    private Long id;

    private String title;

    private String password;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
