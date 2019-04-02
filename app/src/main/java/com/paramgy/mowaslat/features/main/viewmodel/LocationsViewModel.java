package com.paramgy.mowaslat.features.main.viewmodel;

import com.paramgy.mowaslat.data.firestore.callbacks.FirestoreLocationsCallback;
import com.paramgy.mowaslat.data.repository.AppRepository;
import com.paramgy.mowaslat.features.main.contracts.LocationsViewModelContract;

import androidx.lifecycle.ViewModel;

public class LocationsViewModel extends ViewModel implements LocationsViewModelContract {

    private AppRepository appRepository;

    public LocationsViewModel() {
        appRepository = AppRepository.getInstance();
    }


    public void getAllLocations(FirestoreLocationsCallback callback) {
        appRepository.getAllLocations(callback);
    }// end getAllLocations

}
