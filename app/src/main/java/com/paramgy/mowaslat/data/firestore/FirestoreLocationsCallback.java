package com.paramgy.mowaslat.data.firestore;

import com.paramgy.mowaslat.data.model.Location;

import java.util.List;

public interface FirestoreLocationsCallback {
   void locationsListCallback(List<Location> locations);
}
