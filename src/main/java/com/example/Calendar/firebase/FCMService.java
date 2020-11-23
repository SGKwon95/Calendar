package com.example.Calendar.firebase;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.Calendar.model.PushNotificationRequest;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Log
@Service
public class FCMService {

    public void sendMessage(Map<String, String> data, PushNotificationRequest request)
        throws InterruptedException, ExecutionException {
            Message msg = getPreconfiguredMessageWithData(data, request);
            String response = sendAndGetResponse(msg);
            log.info("Sent message with data. Topic: " + request.getTopic() + ", " + response);
        }
        public void sendMessageWithoutData(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
            Message msg = getPreconfiguredMessageWithoutData(request);
            String response = sendAndGetResponse(msg);
            log.info("Sent message without data. Topic: "+ request.getTopic() + ", " + response);
        }

        public void sendMessageToToken(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
            Message msg = getPreconfiguredMessageToToken(request);
            String response = sendAndGetResponse(msg);
            log.info("Sent message to token. Device token: "+request.getToken() + ", " + response);
        }

        private String sendAndGetResponse(Message msg) throws InterruptedException, ExecutionException {
            return FirebaseMessaging.getInstance().sendAsync(msg).get();
        }

        private AndroidConfig getAndroidConfig(String topic) {
            return AndroidConfig.builder()
                    .setTtl(Duration.ofMinutes(60).toMillis()).setCollapseKey(topic)
                    .setPriority(AndroidConfig.Priority.HIGH)
                    .setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
                            .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
        }

        private ApnsConfig getApnsConfig(String topic) {
            return ApnsConfig.builder()
                    .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
        }

        private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
            return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                    .build();
        }

    private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getTopic())
                .build();
    }

    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).putAllData(data).setTopic(request.getTopic())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(request.getTitle(), request.getMessage())
                );
    }
}
