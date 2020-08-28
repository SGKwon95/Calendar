package com.example.Calender.controller;

import com.example.Calender.entity.Calendar;
import com.example.Calender.service.CalendarService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return new ResponseEntity<>(service.todoList(df), HttpStatus.OK);
    }
}
