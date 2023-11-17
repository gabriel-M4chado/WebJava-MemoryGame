package com.memorygame.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;

public class FirebaseService {

    private static final String DATABASE_URL = "https://memorygame-df47d.firebaseio.com";

    static {
        initializeFirebaseApp();
    }

    private static void initializeFirebaseApp() {
        try {
            InputStream serviceAccountStream = FirebaseService.class.getClassLoader()
                    .getResourceAsStream("memorygame-df47d-firebase-adminsdk-cxi03-262d7fd0f7.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
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

    public static boolean validateUserCredentials(String email, String password) {
        try {
            FirebaseAuth.getInstance().getUserByEmail(email);
            /*
             * FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password); =
             * The method signInWithEmailAndPassword(String, String) is undefined for the
             * type FirebaseAuthJava
             */

            return true; // Successful login
        } catch (FirebaseAuthException e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            return false; // Failed login
        }
    }

}
