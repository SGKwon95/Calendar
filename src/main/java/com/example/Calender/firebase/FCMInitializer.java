package com.example.Calender.firebase;

import java.io.IOException;

import javax.annotation.PostConstruct;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Log
@Service
public class FCMInitializer{
    private String firebaseConfigPath = "calenderapp-c1fe5-firebase-adminsdk-840v0-be50a74844.json";

    @PostConstruct
    public void initialize() {
        try {
            log.info(firebaseConfigPath);
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(
                    new ClassPathResource(firebaseConfigPath).getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase app has been initilaized.");
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}