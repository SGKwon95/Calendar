package com.example.Calender.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Calendar {
    public Calendar() {}
    public Calendar(String df)
    {
        this.content = "";
        this.contentId = -1;
        this.date = new Date();
        this.dateString = df;
    }

    private int contentId;
    private Date date;
    private String content;
    private String dateString;
}
