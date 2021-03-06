package com.example.Calendar.service;

import com.example.Calendar.entity.Calendar;

import java.util.List;

public interface CalendarService {
    public void makeTodo(Calendar calendar) throws Exception;
    public void removeTodo(Long todoNo, Long userNo) throws Exception;
    public void modifyTodo(Calendar calendar) throws Exception;
    public List<Calendar> todoList(String date, Long userNo) throws Exception;
}
