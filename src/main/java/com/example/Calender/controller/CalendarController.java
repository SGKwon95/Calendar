package com.example.Calender.controller;

import com.example.Calender.entity.Calendar;
import com.example.Calender.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import lombok.extern.java.Log;

import java.util.List;

@Log
@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class CalendarController {
    @Autowired
    private CalendarService service;

    @GetMapping("/{df}")
    public ResponseEntity<List<Calendar>> searchTodo(@PathVariable String df) throws Exception {
        log.info("toRefresh(), df = "+df);
        return new ResponseEntity<>(service.todoList(df), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Calendar> addTodo(
        @Validated @RequestBody Calendar calendar) throws Exception {
        log.info("ResponseEntity()"+calendar.getDateString());
        service.makeTodo(calendar);

        return new ResponseEntity<>(calendar, HttpStatus.OK);
    }
    
    @GetMapping("/{df}/{todoNo}")
    public ResponseEntity<Void> remove(
            @PathVariable String df,
            @PathVariable Long todoNo) throws Exception {
        log.info("1. remove, todoNo = "+todoNo);
        service.removeTodo(todoNo);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
}
