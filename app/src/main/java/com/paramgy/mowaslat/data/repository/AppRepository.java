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

    public LiveData<Result> getResult(String current, String destination) {
        return fireStoreRepository.getResult(current, destination);
    }

    public void setResultRating(float rating, String resultID, String method, String uniqueID) {
        fireStoreRepository.setResultRating(rating, resultID, method , uniqueID);
    }
}// end AppRepository Class
