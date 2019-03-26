package com.paramgy.mowaslat.data.repository;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.paramgy.mowaslat.data.firestore.FireStoreRepository;
import com.paramgy.mowaslat.data.model.Location;
import com.paramgy.mowaslat.data.model.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    public void getAllLocations(FirestoreCallback firestoreCallback) {
        fireStoreRepository.getLocations(firestoreCallback);
    }


    public Result getResult(String current, String destination, int method) {
        return fireStoreRepository.getResult(current, destination, method);
    }


}// end AppRepository Class
