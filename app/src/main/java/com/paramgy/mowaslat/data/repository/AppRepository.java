package com.paramgy.mowaslat.data.repository;

import com.paramgy.mowaslat.data.firestore.FireStoreRepository;
import com.paramgy.mowaslat.data.firestore.FirestoreLocationsCallback;
import com.paramgy.mowaslat.data.firestore.FirestoreResultCallback;

public class AppRepository {
    FireStoreRepository fireStoreRepository;
    public static final String TAG = "AppRepository";
    /*
    Singleton pattern
     */
    private static AppRepository appRepository;

    private AppRepository() {
        fireStoreRepository = new FireStoreRepository();
    }

    public static synchronized AppRepository getInstance() {
        if (appRepository == null) {
            appRepository = new AppRepository();
        }
        return appRepository;
    }

    //***************** Operations ********************//
    public void getAllLocations(FirestoreLocationsCallback firestoreLocationsCallback) {
        fireStoreRepository.getLocations(firestoreLocationsCallback);
    }

    public void getResult(FirestoreResultCallback callback, String current, String destination, int method) {
        fireStoreRepository.getResult(callback, current, destination, method);
    }

}// end AppRepository Class
