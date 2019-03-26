package com.paramgy.mowaslat.view_model;

import com.paramgy.mowaslat.data.firestore.FirestoreLocationsCallback;

public interface AppViewModelInterface {
    //Get List Of all Locations in the Database
    void getAllLocations(FirestoreLocationsCallback callback);
}
