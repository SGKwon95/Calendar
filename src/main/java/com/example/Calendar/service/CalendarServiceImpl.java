package com.example.Calendar.service;

import com.example.Calendar.entity.Calendar;
import com.example.Calendar.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{

    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public void makeTodo(Calendar calendar) throws Exception {
        calendarRepository.makeTodo(calendar);
    }

    @Override
    public void removeTodo(Long todoNo, Long userNo) throws Exception {
        calendarRepository.removeTodo(todoNo, userNo);
    }

    @Override
    public void modifyTodo(Calendar calendar) throws Exception {
        calendarRepository.modifyTodo(calendar);
    }

    @Override
    public List<Calendar> todoList(String df, Long userNo) throws Exception {
        return calendarRepository.list(df, userNo);
    }
}
