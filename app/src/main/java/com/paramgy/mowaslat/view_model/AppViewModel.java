package com.paramgy.mowaslat.view_model;

import com.paramgy.mowaslat.data.repository.AppRepository;
import com.paramgy.mowaslat.data.firestore.FirestoreLocationsCallback;

import androidx.lifecycle.ViewModel;

public class AppViewModel extends ViewModel implements AppViewModelInterface {

    private AppRepository appRepository;
    private static final String TAG = "AppViewModel";


    public AppViewModel() {
        appRepository = AppRepository.getInstance();
    }


    public void getAllLocations(FirestoreLocationsCallback callback) {
        appRepository.getAllLocations(callback);
    }// end getAllLocations

}
