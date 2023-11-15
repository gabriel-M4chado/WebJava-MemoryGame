package com.memorygame.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseService {

    private static final String DATABASE_URL = "https://memorygame-df47d.firebaseio.com";

    static {
        initializeFirebaseApp();
    }

    private static void initializeFirebaseApp() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/memorygame-df47d-firebase-adminsdk-cxi03-262d7fd0f7.json"); // Replace with the path to your serviceAccountKey.json file
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseReference getDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

}
