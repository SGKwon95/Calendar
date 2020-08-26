package com.example.Calender.controller;

import com.example.Calender.entity.Calendar;
import com.example.Calender.service.CalendarService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class CalendarController {
    @Autowired
    private CalendarService service;

    @GetMapping("")
    public ResponseEntity<List<Calendar>> list() throws Exception {
        log.info("등록된 스케줄 불러오기");

        return new ResponseEntity<>(service.todoList(), HttpStatus.OK);
    }

}
