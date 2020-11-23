package com.example.Calendar.controller;

import com.example.Calendar.model.PushNotificationRequest;
import com.example.Calendar.model.PushNotificationResponse;
import com.example.Calendar.service.PushNotificationService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class PushNotificationController {
    private PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/notification")
    public ResponseEntity<PushNotificationResponse> sendNotification(@RequestBody PushNotificationRequest request) {
        log.info("notification");
        pushNotificationService.sendPushNotificaitonWithoutData(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(),
                "Notification has been sent."),HttpStatus.OK);
    }
}
