package com.paramgy.mowaslat.data.repository;

import com.paramgy.mowaslat.data.firestore.FireStoreRepository;
import com.paramgy.mowaslat.data.model.pojos.Location;
import com.paramgy.mowaslat.data.model.pojos.Result;

import java.util.List;

import androidx.lifecycle.LiveData;

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
    public LiveData<List<Location>> getAllLocations() {
        return fireStoreRepository.getLocations();
    }

    public LiveData<Result> getResult(String current, String destination, int method) {
        return fireStoreRepository.getResult(current, destination, method);
    }

    public void setResultRating(float rating, String resultID, String uniqueID) {
        fireStoreRepository.setResultRating(rating, resultID, uniqueID);
    }

    public void sendMsg(String text) {
        //todo send msg via email
    }
}// end AppRepository Class
