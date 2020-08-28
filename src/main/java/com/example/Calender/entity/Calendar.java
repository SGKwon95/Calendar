package com.example.Calender.entity;

import java.util.Date;

public class Calendar {

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    private int contentId;
    private Date date;
    private String content;

    public Calendar(Date date)
    {
        this.content = "";
        this.contentId = -1;
        this.date = date;
    }
    public Calendar() {}
}
