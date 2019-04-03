package com.paramgy.mowaslat.features.main.viewmodel;

import com.paramgy.mowaslat.data.model.pojos.Location;
import com.paramgy.mowaslat.data.repository.AppRepository;
import com.paramgy.mowaslat.features.main.contracts.LocationsViewModelContract;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class LocationsViewModel extends ViewModel implements LocationsViewModelContract {

    private AppRepository appRepository;

    public LocationsViewModel() {
        appRepository = AppRepository.getInstance();
    }


    public LiveData<List<Location>> getAllLocations() {
        return appRepository.getAllLocations();
    }// end getAllLocations

}
