package com.example.Calender.service;

import com.example.Calender.firebase.FCMService;
import com.example.Calender.model.PushNotificationRequest;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Log
@Service
public class PushNotificationService {
    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    public void sendPushNotificaitonWithoutData(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToToken(request);
        } catch (InterruptedException | ExecutionException e) {
            log.info(e.getMessage());
        }
    }
}
