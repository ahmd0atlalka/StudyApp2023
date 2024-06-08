package com.example.studyapp2023.Domain;

import java.util.Date;

public class CoursesDomain {
    private String title;
    private String description;
    private String picPath;
    private String date;

    public CoursesDomain(String title, String description, String picPath, String date) {
        this.title = title;
        this.description = description;
        this.picPath = picPath;
        this.date = date;
    }

    // Add getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
