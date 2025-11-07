package com.example.model;

import java.time.LocalDateTime;

public class NewsHeadline {
    private LocalDateTime dateTime;
    private String title;
    private String summary;

    public NewsHeadline(LocalDateTime dateTime, String title, String summary) {
        this.dateTime = dateTime;
        this.title = title;
        this.summary = summary;
    }

    public LocalDateTime getDateTime() { return dateTime; }
    public String getTitle() { return title; }
    public String getSummary() { return summary; }
}