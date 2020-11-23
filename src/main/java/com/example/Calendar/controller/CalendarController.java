package com.example.Calendar.controller;

import com.example.Calendar.entity.Calendar;
import com.example.Calendar.service.CalendarService;
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

    @GetMapping("/search/{df}/{userNo}")
    public ResponseEntity<List<Calendar>> searchTodo(
        @PathVariable String df,
        @PathVariable long userNo) throws Exception {
        log.info("from Refresh() in vue, df = "+df);
        return new ResponseEntity<>(service.todoList(df,userNo), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Calendar> addTodo(
        @Validated @RequestBody Calendar calendar) throws Exception {
        log.info("ResponseEntity()"+calendar.getDateString());
        service.makeTodo(calendar);

        return new ResponseEntity<>(calendar, HttpStatus.OK);
    }
    
    @GetMapping("/remove/{df}/{todoNo}/{userNo}")
    public ResponseEntity<Void> remove(
            @PathVariable String df,
            @PathVariable Long todoNo,
            @PathVariable Long userNo) throws Exception {
        log.info("1. remove, todoNo = "+todoNo);
        log.info("1. remove, todoNo = "+userNo);
        service.removeTodo(todoNo, userNo);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
}
