package com.paramgy.mowaslat.view_model;

import com.paramgy.mowaslat.data.model.Location;
import java.util.List;
import androidx.lifecycle.LiveData;

public interface AppViewModelInterface {
    //Get List Of all Locations in the Database
    LiveData<List<Location>> getAllLocations();
}
