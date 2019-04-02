package com.paramgy.mowaslat.data.repository;

import com.paramgy.mowaslat.data.firestore.FireStoreRepository;
import com.paramgy.mowaslat.data.firestore.callbacks.FirestoreLocationsCallback;
import com.paramgy.mowaslat.data.firestore.callbacks.FirestoreResultCallback;

public class AppRepository {
    private FireStoreRepository fireStoreRepository;

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

    public void setResultRating(float rating, String resultID, String uniqueID) {
        fireStoreRepository.setResultRating(rating, resultID, uniqueID);
    }

    public void sendMsg(String text) {
        //todo send msg via email
    }
}// end AppRepository Class
