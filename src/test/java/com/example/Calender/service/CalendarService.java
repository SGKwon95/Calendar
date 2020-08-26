package com.example.Calender.service;

import com.example.Calender.entity.Calendar;

import java.util.List;

public interface CalendarService {
    public void makeTodo(Calendar calendar) throws Exception;
    public void removeTodo(Calendar calendar) throws Exception;
    public Calendar readTodo(Calendar calendar) throws Exception;
    public void modifyTodo(Calendar calendar) throws Exception;
    public List<Calendar> todoList() throws Exception;
}
