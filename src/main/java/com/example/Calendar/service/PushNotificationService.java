package com.example.Calendar.service;

import com.example.Calendar.firebase.FCMService;
import com.example.Calendar.model.PushNotificationRequest;
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
