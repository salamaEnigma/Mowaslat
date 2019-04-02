package com.paramgy.mowaslat.features.main.contracts;

import com.paramgy.mowaslat.data.firestore.callbacks.FirestoreLocationsCallback;

public interface LocationsViewModelContract {
    //Get List Of all Locations in the Database
    void getAllLocations(FirestoreLocationsCallback callback);
}
