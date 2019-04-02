package com.paramgy.mowaslat.data.firestore.callbacks;

import com.paramgy.mowaslat.data.model.pojos.Location;

import java.util.List;

public interface FirestoreLocationsCallback {
   void locationsListCallback(List<Location> locations);
}
