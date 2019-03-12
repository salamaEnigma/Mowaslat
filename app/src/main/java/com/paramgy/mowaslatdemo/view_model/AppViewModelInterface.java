package com.paramgy.mowaslatdemo.view_model;

import com.paramgy.mowaslatdemo.data.model.Location;
import java.util.List;
import androidx.lifecycle.LiveData;

public interface AppViewModelInterface {
    //Get Result From the Repository
    void getResult(String currentLocation, String destination, int method);
    //Get List Of all Locations in the Database
    LiveData<List<Location>> getAllLocations();
}
