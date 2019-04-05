package com.paramgy.mowaslat.data.repository;

import com.paramgy.mowaslat.data.firestore.FireStoreRepository;
import com.paramgy.mowaslat.data.model.pojos.Location;
import com.paramgy.mowaslat.data.model.pojos.NewResult;

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

    public LiveData<NewResult> getNewResult(String current, String destination,String method) {
        return fireStoreRepository.getNewResult(current, destination,method);
    }

    public void setResultRating(float rating, String resultID, String uniqueID) {
        fireStoreRepository.setResultRating(rating, resultID, uniqueID);
    }
}// end AppRepository Class
