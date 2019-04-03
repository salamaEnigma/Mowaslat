package com.paramgy.mowaslat.features.main.contracts;

import com.paramgy.mowaslat.data.model.pojos.Location;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface LocationsViewModelContract {
    //Get List Of all Locations in the Database
    LiveData<List<Location>> getAllLocations();
}
