package com.example.Calendar.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DetailErrorInfoApi {
    private String target;
    private String message;
}
