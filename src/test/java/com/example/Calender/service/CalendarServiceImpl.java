package com.example.Calender.service;

import com.example.Calender.entity.Calendar;
import com.example.Calender.repository.CalendarRepository;
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
    public void removeTodo(Calendar calendar) throws Exception {
        calendarRepository.removeTodo(calendar);
    }

    @Override
    public Calendar readTodo(Calendar calendar) throws Exception {
        return calendarRepository.readTodo(calendar);
    }

    @Override
    public void modifyTodo(Calendar calendar) throws Exception {
        calendarRepository.modifyTodo(calendar);
    }

    @Override
    public List<Calendar> todoList() throws Exception {
        return calendarRepository.list();
    }
}
